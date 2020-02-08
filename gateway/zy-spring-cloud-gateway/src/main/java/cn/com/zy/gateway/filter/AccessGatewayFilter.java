package cn.com.zy.gateway.filter;

import cn.com.zy.util.context.UserContextHolder;
import cn.com.zy.util.jwt.JwtProperties;
import cn.com.zy.util.jwt.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


@Slf4j
public class AccessGatewayFilter implements GlobalFilter {

    private static final PathMatcher mathcher = new AntPathMatcher();

    @Autowired
    private JwtProperties jwtProperties;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String requestUri = request.getPath().pathWithinApplication().value();

        //不进行拦截的地址
        for (String excludePathPattern : jwtProperties.getExcludePathPatterns()) {
            if (mathcher.match(excludePathPattern,requestUri)) {
                log.info("规则【" + excludePathPattern + "】匹配了当前请求【" + requestUri + "】终止继续拦截");
                ServerHttpRequest build = request.mutate().build();
                return chain.filter(exchange.mutate().request(build).build());
            }
        }
        //重新封装request，传给下一级，由于post的body只能订阅一次，所以要再次封装请求到request才行，不然会报错请求已经订阅过
        log.info("防止没有网络隔离情况下，服务被直接代理访问，重新设置请求中token信息，设置为服务隔离层token： " +  UserContextHolder.getJwtToken());
        ServerHttpRequest token = exchange.getRequest().mutate().headers((httpHeaders) -> {
            httpHeaders.remove(JwtUtils.HEAD_TOKEN_KEY);
            httpHeaders.add(JwtUtils.HEAD_TOKEN_KEY, UserContextHolder.getJwtToken());
        }).build();
        return chain.filter(exchange.mutate().request(token).build());
    }
}
