package cn.com.zy.gateway.security.authentication.jwt;

import cn.com.zy.gateway.feign.SystemAdminAPI;
import cn.com.zy.util.context.UserContextHolder;
import cn.com.zy.util.enums.RedisKeyEnum;
import cn.com.zy.util.jwt.JwtProperties;
import cn.com.zy.util.jwt.JwtUtils;
import com.alibaba.fastjson.JSONArray;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.NonceExpiredException;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class JwtReactiveUserDetailsService implements ReactiveUserDetailsService {

    @Autowired
    private JwtProperties jwtProperties;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    @Lazy
    private SystemAdminAPI systemAdminAPI;
    /**
     * @description: <p>验证和解析token，以及加载UserDetails</p>
     * @param: String token
     */
    @Override
    public Mono<UserDetails> findByUsername(String token) {
        log.debug(String.format("被验证的token为:%s", token));
        String userId = validateToken(token);
        log.debug(String.format("获取的ID为:%s", userId));
        Collection<? extends GrantedAuthority> authorities = Arrays.stream(getUserPermissions(userId)).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return Mono.just(new JwtUserDetails(userId, authorities));
    }

    /**
     * @description: <p>验证token</p>
     */
    public String validateToken(String token) {
        try {
            // 优先检车是否为静态token
            JwtUtils.TokenDetail detail = JwtUtils.verify(token, jwtProperties.getGatewaySecret());
            initContextHolder(detail.getUserId(), token);
            // redis层验证
            String redisToken = redisTemplate.opsForValue().get(JwtUtils.getRedisTokenKey(JwtUtils.GATEWAY_REDIS_USER_TOKEN_KEY_PREFIX, detail.getJwtId()));

            if (detail.isStatic) {
                if (redisToken == null && !Boolean.TRUE.equals(systemAdminAPI.verifyStaticTokenHandle(detail.getJwtId()))) {
                    // 伪登录验证
                    throw new TokenExpiredException("token不可用");
                }
                redisToken = redisTemplate.opsForValue().get(JwtUtils.getRedisTokenKey(JwtUtils.GATEWAY_REDIS_USER_TOKEN_KEY_PREFIX, detail.getJwtId()));
            }
            if (redisToken == null) {
                log.info("redis中token为null");
                throw new TokenExpiredException("回话过期");
            }
            return detail.getUserId();
        } catch (TokenExpiredException e) {
            log.error("token过期", e);
            throw new NonceExpiredException("token过期", e);
        } catch (JWTVerificationException e) {
            log.error("非法的token信息", e);
            throw new BadCredentialsException("非法的token信息", e);
        }
    }

    private String[] getUserPermissions(String userId) {
        List<String>  roles = new ArrayList<String>();
        log.debug("读取Redis中的用户权限信息");
        String key = RedisKeyEnum.USER_PERMISSIONS_KEY_PREFIX.toString() + userId;
        String value = redisTemplate.opsForValue().get(key);
        if (value == null || "".equals(value.trim())) {
            log.debug("调用服务，重新初始化redis中用户权限信息");
            systemAdminAPI.getUserPermissions();
            value = redisTemplate.opsForValue().get(key);
            log.debug("正在重新读取Redis中的用户权限信息");
        }
        if (value != null && !"".equals(value.trim())) {
            roles = JSONArray.parseArray(value).toJavaList(String.class);
        }
        log.debug("用户没有权限信息");
        return roles.toArray(new String[roles.size()]);
    }

    private void initContextHolder(String userId, String token) {
        Map<String, String> context = new HashMap<String, String>();
        context.put(UserContextHolder.USER_ID_KEY, userId);
        log.debug("正在更新本地token(替换秘钥重新加密)，更新前：" + token);
        token = JwtUtils.changeTokenSecret(token, jwtProperties.getGatewaySecret(), jwtProperties.getServiceSecret());
        context.put(UserContextHolder.JWT_KEY, token);
        log.debug("正在更新本地token(替换秘钥重新加密)，更新后：" + token);
        UserContextHolder.setContext(context);
    }
}
