package cn.com.zy.gateway.config;

import cn.com.zy.gateway.security.PathPatternsServerWebExchangeMatcher;
import cn.com.zy.gateway.security.RedisMemorySecurityMetadataSource;
import cn.com.zy.gateway.security.authentication.HttpBasicServerAuthenticationEntryPoint;
import cn.com.zy.gateway.security.authentication.ServerAuthenticationEntryPointFailureHandler;
import cn.com.zy.gateway.security.authentication.jwt.JwtAuthenticationConverter;
import cn.com.zy.gateway.security.authentication.jwt.JwtReactiveAuthenticationManager;
import cn.com.zy.gateway.security.authentication.jwt.JwtReactiveUserDetailsService;
import cn.com.zy.gateway.security.authorization.AuthorityReactiveAuthorizationManager;
import cn.com.zy.gateway.security.authorization.HttpBasicServerAccessDeniedHandler;
import cn.com.zy.util.jwt.JwtProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.DelegatingReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.context.WebSessionServerSecurityContextRepository;
import org.springframework.security.web.server.util.matcher.NegatedServerWebExchangeMatcher;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;

import java.util.Arrays;

@EnableWebFluxSecurity
@Configuration
@Slf4j
public class SecurityWebFluxConfigration {

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * @description: <p>spring security webflux 配置入口</p>
     */
    @Bean
    SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http) throws Exception {

        log.info("正在初始化 ServerHttpSecurity...");
        http.httpBasic().disable()
            .csrf().disable()  //CRSF禁用
            .formLogin().disable() //禁用form登录
            .headers()
                .frameOptions().disable()
                .cache().disable()
            .and()
                .cors()  //支持跨域
            .and()
                .authorizeExchange()
                .matchers(PathPatternsServerWebExchangeMatcher.creator(jwtProperties.getExcludePathPatterns())).permitAll()
                .pathMatchers("/**").access(authorityReactiveAuthorizationManager())
            .and()
                .addFilterAt(authenticationWebFilter(), SecurityWebFiltersOrder.AUTHENTICATION)
                .exceptionHandling()
                    .authenticationEntryPoint(new HttpBasicServerAuthenticationEntryPoint())
                    .accessDeniedHandler(new HttpBasicServerAccessDeniedHandler());
        log.info("ServerHttpSecurity 初始化完成");
        return http.build();
    }
    /**
     * @description: <p>认证过滤器</p>
     */
    @Bean
    public AuthenticationWebFilter authenticationWebFilter() {
        log.info("正在初始化 AuthenticationWebFilter...");
        AuthenticationWebFilter authenticationWebFilter = new AuthenticationWebFilter(delegatingReactiveAuthenticationManager());
        authenticationWebFilter.setServerAuthenticationConverter(new JwtAuthenticationConverter());
        NegatedServerWebExchangeMatcher negateWhiteList = new NegatedServerWebExchangeMatcher(ServerWebExchangeMatchers.pathMatchers(jwtProperties.getExcludePathPatterns()));
        authenticationWebFilter.setRequiresAuthenticationMatcher(negateWhiteList);
        authenticationWebFilter.setSecurityContextRepository(new WebSessionServerSecurityContextRepository());
        authenticationWebFilter.setAuthenticationFailureHandler(authenticationEntryPointFailureHandler());
        log.info("AuthenticationWebFilter 初始化完成");
        return authenticationWebFilter;
    }
    /**
     * @description: <p>认证管理器</p>
     */
    @Bean
    public DelegatingReactiveAuthenticationManager delegatingReactiveAuthenticationManager() {
        log.info("正在初始化 DelegatingReactiveAuthenticationManager...");
        DelegatingReactiveAuthenticationManager authenticationManager = new DelegatingReactiveAuthenticationManager(Arrays.asList(
                new JwtReactiveAuthenticationManager(jwtReactiveUserDetailsService())
        ));
        log.info("DelegatingReactiveAuthenticationManager 初始化完成");
        return authenticationManager;
    }
    /**
     * @description: <p>系统管理服务用户名和密码认证 UserDetails</p>
     */
    @Bean
    public JwtReactiveUserDetailsService jwtReactiveUserDetailsService() {
        return new JwtReactiveUserDetailsService();
    }
    @Bean
    public ServerAuthenticationEntryPointFailureHandler authenticationEntryPointFailureHandler() {
        return new ServerAuthenticationEntryPointFailureHandler();
    }
    /**
     * @description: <p>API级别授权管理器</p>
     */
    @Bean
    public AuthorityReactiveAuthorizationManager authorityReactiveAuthorizationManager() {
        log.info("正在初始化 AuthorityReactiveAuthorizationManager...");
        AuthorityReactiveAuthorizationManager authorizationManager = new AuthorityReactiveAuthorizationManager(securityMetadataSource());
        log.info("AuthorityReactiveAuthorizationManager 初始化完成");
        return authorizationManager;
    }
    /**
     * @description: <p></p>
     */
    @Bean
    public RedisMemorySecurityMetadataSource securityMetadataSource() {
        return new RedisMemorySecurityMetadataSource();
    }
}
