package cn.com.zy.gateway.config;

import cn.com.zy.gateway.filter.AccessGatewayFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccessGatewayFilterConfigration {
    /**
     * @description: <p>全局网关层token移除，便于fegin准确追加后端服务隔离层token</p>
     */
    @Bean
    AccessGatewayFilter accessGatewayFilter() {
        return new AccessGatewayFilter();
    }
}
