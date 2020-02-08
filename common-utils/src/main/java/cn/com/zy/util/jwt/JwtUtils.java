package cn.com.zy.util.jwt;



/*
* jwt工具类
*
* */

import cn.com.zy.util.context.UserContextHolder;
import cn.com.zy.util.enums.JwtIssuer;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
public class JwtUtils {
    public final static String JWT_PREFIX = "Bearer";
    public final static String HEAD_TOKEN_KEY = "Authorization";
    public final static String PARAMETER_TOKEN_KEY = "token";
    public final static String SERVICE_REDIS_USER_TOKEN_KEY_PREFIX = "SERVICE_USER_TOKEN_";
    public final static String GATEWAY_REDIS_USER_TOKEN_KEY_PREFIX = "GATEWAY_USER_TOKEN_";

    public final static String SPECIAL_REQUEST_HEADED_KEY = "SPECIAL_REQUEST_HEADED_KEY";


    public static void main(String args[]) {
        // 生成一个静态token
        JwtUtils.TokenDetail detail = new JwtUtils.TokenDetail();
        detail.setStatic(true)
                .setJwtId(UUID.randomUUID().toString())
                .setUserId("admin")
                .setJwtIssuer(JwtIssuer.STATIC_TOKEN);
        System.out.println(create(detail, "taiji.com.cn", 52560000));
    }

    /**
     * 统一的从request获取token方法
     */
    public static String getToken(HttpServletRequest request){
        log.info("正在尝试从请求header中获取token");
        String authorization = request.getHeader(HEAD_TOKEN_KEY);
        if (authorization == null){
            log.info("正在尝试从请求参数中获取token");
            authorization = (String) request.getParameter(PARAMETER_TOKEN_KEY);
        }
        log.info("获取token信息为：" + authorization);
        return authorization;
    }

    /**
     * @description: <p>JWT生成器，采用HMAC256算法</p>
     * @param detail 信息主体
     * @param secret 秘钥
     * @param amount token有效期（分钟）
     * @return 新生成的 token
     */
    public static String create(TokenDetail detail, String secret, Integer amount) {
        Date now = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);
        cal.add(Calendar.MINUTE, amount);
        String jwt = JWT.create()
                // Redis KEY使用，支持多登陆,通过UUID方式隔离同一个用户的不同token
                .withJWTId(detail.getJwtId())
                // 编号, 保持与用户ID相同
                .withKeyId(detail.getUserId())
                // 签发时间
                .withIssuedAt(now)
                // 签发人
                .withIssuer(detail.getJwtIssuer().name())
                // 过期时间
                .withExpiresAt(cal.getTime())
                .withNotBefore(now)
                .withSubject(JSONObject.toJSONString(detail))
                .sign(Algorithm.HMAC256(secret));
        log.info("生成的token为： " + jwt);
        return jwt;
    }

    /**
     * jwt生成器,采用HMAC256算法
     * @param user 用户信息
     * @param secret 秘钥
     * @param amount token有效期（分钟）
     * @param redisKeyPrefix redis token key前缀
     * @param redisTemplate 可用的redis连接
     */
    public static String create(JSONObject user, String secret, Integer amount, String redisKeyPrefix, StringRedisTemplate redisTemplate){
        JSONObject jwtSubject = new JSONObject();
        Date now = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);
        cal.add(Calendar.MINUTE,amount);
        String keyId = (String) user.get("id");
        String jwtId = UUID.randomUUID().toString();
        String name = (String) user.get("name");
        String jwt = JWT.create()
                // Redis KEY使用，支持多登陆,通过UUID方式隔离同一个用户的不同token
                .withJWTId(jwtId)
                // 编号, 保持与用户ID相同
                .withKeyId(keyId)
                // 签发时间
                .withIssuedAt(now)
                // 签发人
                .withIssuer(name)
                // 过期时间
                .withExpiresAt(cal.getTime())
                .withNotBefore(now)
                .withSubject(jwtSubject.toJSONString())
                .sign(Algorithm.HMAC256(secret));
        redisTemplate.opsForValue().set(getRedisTokenKey(redisKeyPrefix,jwtId),jwt,amount, TimeUnit.MINUTES);
        log.info("生成的token为： " + jwt);
        return jwt;
    }


    /**
     * 添加至redis
     * @param token
     * @param redisSuffix
     * @param amount
     * @param redisKeyPrefix
     * @param redisTemplate
     */
    public static void add2Redis(String token, Integer amount, String redisKeyPrefix, String redisSuffix, StringRedisTemplate redisTemplate) {
        redisTemplate.opsForValue().set(getRedisTokenKey(redisKeyPrefix, redisSuffix), token, amount, TimeUnit.MINUTES);
    }


    /**
     * @description: <p>双重验证token，并返回jwtId 或 userId</p>
     * @param token 用户提交的jwt token
     * @param secret 秘钥
     * @param redisKeyPrefix redis token key前缀
     * @param redisTemplate 可用的redis连接
     * @return jwtId 或 userId
     */
    public static String verify(String token, String secret, String redisKeyPrefix, StringRedisTemplate redisTemplate){
        String userId = verify(token, secret, true);
        String jwtId = verify(token,secret,false);
        String redisToken = redisTemplate.opsForValue().get(getRedisTokenKey(redisKeyPrefix,jwtId));
        if (redisToken == null) {
            log.info("redis中token为null");
            throw new TokenExpiredException("回话过期");
        }
        return userId;
    }

/**
 * @description: <p>验证token，并返回jwtId 或 userId</p>
 */
    public static TokenDetail verify(String token, String secret) {
        log.info("被验证的token为： " + token);
        if (token == null) {
            throw new JWTVerificationException("缺失Token信息");
        }
        token = token.replaceFirst(JWT_PREFIX,"");
        com.auth0.jwt.JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret)).build();
        DecodedJWT jwt = verifier.verify(token);

        return JSONObject.toJavaObject(JSONObject.parseObject(jwt.getSubject()), TokenDetail.class);
    }
    public static String verify(String token,String sectet,boolean isUserId){
        log.info("被验证的token为:" + token);
        if (token == null){
            throw new JWTVerificationException("缺失token信息");
        }
        token = token.replaceFirst(JWT_PREFIX,"");
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(sectet)).build();
        DecodedJWT jwt = verifier.verify(token);
        if (isUserId){
            return jwt.getKeyId();
        }
        return jwt.getId();
    }

    public static String changeTokenSecret(String token,String oldSecret,String newSecret){
        if (token == null){
            log.info("重新加密的token信息为null");
            return null;
        }
        token = token.replaceFirst(JWT_PREFIX,"");
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(oldSecret)).build();
        DecodedJWT jwt = verifier.verify(token);
        return JWT_PREFIX + JWT.create()
                .withJWTId(jwt.getId())
                .withKeyId(jwt.getKeyId())
                .withIssuedAt(jwt.getIssuedAt())
                .withIssuer(jwt.getIssuer())
                .withExpiresAt(jwt.getExpiresAt())
                .withNotBefore(jwt.getNotBefore())
                .withSubject(jwt.getSubject())
                .sign(Algorithm.HMAC256(newSecret));
    }

    /**
     * @description: <p>退出用户登录</p>
     * @param redisTemplate 可用的redis连接
     * @return 用户ID
     */
    public static void logout(StringRedisTemplate redisTemplate, String secret) {
        String token = UserContextHolder.getJwtToken();
        if (token != null && !"".equals(token.trim())) {
            TokenDetail detail = null;
            try {
                detail = verify(token, secret);
            } catch (Exception e) {
                detail = null;
            }
            if (detail != null) {
                redisTemplate.delete(getRedisTokenKey(SERVICE_REDIS_USER_TOKEN_KEY_PREFIX, detail.getJwtId()));
                redisTemplate.delete(getRedisTokenKey(GATEWAY_REDIS_USER_TOKEN_KEY_PREFIX, detail.getJwtId()));
                log.info("redis中token已销毁");
            }
        }
        UserContextHolder.setContext(null);
    }
//    public static void logout(StringRedisTemplate redisTemplate,String secret){
//        String token = UserContextHolder.getJwtToken();
//        if (token != null && "".equals(token.trim())){
//            String jwtId = null;
//            try {
//                jwtId = verify(token, secret, false);
//            } catch (Exception e) {
//                jwtId = null;
//            }
//            if (jwtId != null){
//                redisTemplate.delete(getRedisTokenKey(SERVICE_REDIS_USER_TOKEN_KEY_PREFIX, jwtId));
//                redisTemplate.delete(getRedisTokenKey(GATEWAY_REDIS_USER_TOKEN_KEY_PREFIX, jwtId));
//                log.info("redis中token已销毁");
//            }
//        }
//        UserContextHolder.setContext(null);
//    }

    public static String getRedisTokenKey(String prefix, String suffix) {
        return prefix + suffix;
    }










    @Data
    @Accessors(chain = true)
    @NoArgsConstructor
    public static class TokenDetail {
        public boolean isStatic = false;
        public String jwtId = null;
        public String userId = null;
        public JwtIssuer jwtIssuer;

        public TokenDetail(boolean isStatic, String jwtId, String userId, JwtIssuer jwtIssuer){
            this.isStatic = isStatic;
            this.jwtId = jwtId;
            this.userId = userId;
            this.jwtIssuer = jwtIssuer;
        }
    }
}
