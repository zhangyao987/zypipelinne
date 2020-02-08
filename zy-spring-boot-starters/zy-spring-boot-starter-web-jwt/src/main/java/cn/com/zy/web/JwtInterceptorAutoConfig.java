package cn.com.zy.web;

import cn.com.zy.util.jwt.JwtProperties;
import cn.com.zy.web.interceptor.JWTInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @description: <p> jwt 拦截请求，并解析token，设置线程用户上下文</p>
 */


public class JwtInterceptorAutoConfig implements WebMvcConfigurer {

    @Autowired
    protected JwtProperties properties;

    /**
     * @description: <p>JWT拦截器，便于组件注入</p>
     */
    @Bean
    public JWTInterceptor jwtInterceptor(){
        return new JWTInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(jwtInterceptor());
        registration.addPathPatterns("/**");
        registration.excludePathPatterns(properties.getExcludePathPatterns());
    }
}
