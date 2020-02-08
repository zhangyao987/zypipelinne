package cn.com.zy.web.interceptor;


import cn.com.zy.util.context.UserContextHolder;
import cn.com.zy.util.exceptions.ControllerFieldCheckException;
import cn.com.zy.util.jwt.JwtProperties;
import cn.com.zy.util.jwt.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: <p>JWT 拦截器，双重验证</p>
 */

@Slf4j
public class JWTInterceptor implements HandlerInterceptor {

    @Value("${spring.application.name}")
    private String server;
    @Autowired
    protected JwtProperties properties;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        log.info("JWTInterceptor.prehandle 正在处理 server:{} - uri{} ", server, uri);
        String authorization = JwtUtils.getToken(request);
        if (authorization == null) {
            throw new ControllerFieldCheckException( uri+ "却是token信息");
        }
        //验证jwt，并为线程绑定当前登录人的信息
        Map<String, String> context = new HashMap<>();
        context.put(UserContextHolder.USER_ID_KEY, JwtUtils.verify(authorization, properties.getServiceSecret()).getUserId());
        context.put(UserContextHolder.JWT_KEY, authorization);
        UserContextHolder.setContext(context);
        log.info("JWTInterceptor.preHandle 完成UserContextHolder用户信息线程绑定 server:{} - uri:{} ", server, uri);
        return true;
    }

}
