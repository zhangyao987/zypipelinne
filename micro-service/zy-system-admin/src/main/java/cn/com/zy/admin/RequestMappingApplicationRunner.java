package cn.com.zy.admin;


import cn.com.zy.admin.entity.TSecurity;
import cn.com.zy.admin.service.ITSecurityService;
import cn.com.zy.util.context.UserContextHolder;
import cn.com.zy.util.enums.Boolean;
import cn.com.zy.util.enums.JwtIssuer;
import cn.com.zy.util.jwt.JwtProperties;
import cn.com.zy.util.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @description: <p>服务启动完成后强行更新服务所有的controller信息</p>
 */
public class RequestMappingApplicationRunner implements ApplicationRunner {

    @Value("${spring.application.name}")
    private String serviceName;
    @Value("${taiji.init-micro-service-security}")
    private boolean initMicroServiceSecurity;

    @Autowired
    private RequestMappingHandlerMapping handlerMapping;
    @Autowired
    protected JwtProperties properties;
    @Autowired
    protected StringRedisTemplate redisTemplate;
    @Autowired
    protected ITSecurityService securityService;

    @Override
    public void run(ApplicationArguments args) {
        if (!initMicroServiceSecurity) { return; }
        Iterator<?> iterator = this.handlerMapping.getHandlerMethods().entrySet().iterator();
        Map<String, TSecurity> authoritieMap = new HashMap<String, TSecurity>();
        while(iterator.hasNext()){
            Map.Entry entry = (Map.Entry) iterator.next();
            RequestMappingInfo mappingInfo = (RequestMappingInfo)entry.getKey();
            mappingInfo.getPatternsCondition().getPatterns().stream().map(p -> {
                TSecurity security = new TSecurity();
                String id = DigestUtils.md5DigestAsHex(("/" + serviceName + p).getBytes());
                security.setId(id);
                security.setServiceId(serviceName);
                security.setSecurityDef(p);
                security.setFromSystem(Integer.valueOf(Boolean.TRUE.getKey()));
                security.setName(mappingInfo.getName());
                security.setRemark(mappingInfo.toString());
                authoritieMap.put(id, security);
                return security;
            }).collect(Collectors.toList());
        }
        // 服务启动并没有回话，临时设置用户信息上下文
        Map<String, String> map = new HashMap<String, String>();
        JwtUtils.TokenDetail detail = new JwtUtils.TokenDetail();
        detail.setStatic(true)
                .setJwtId(UUID.randomUUID().toString())
                .setUserId("admin")
                .setJwtIssuer(JwtIssuer.STATIC_TOKEN);

        map.put(UserContextHolder.USER_ID_KEY, detail.getUserId());
        map.put(UserContextHolder.JWT_KEY, JwtUtils.create(detail, properties.getServiceSecret(), 52560000));
        UserContextHolder.setContext(map);
        securityService.initAuthorities(authoritieMap.values().stream().collect(Collectors.toList()));
        JwtUtils.logout(redisTemplate, properties.getServiceSecret());
    }
}
