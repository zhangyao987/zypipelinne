package cn.com.zy.gateway.feign;

import cn.com.zy.gateway.feign.fallback.SystemAdminAPIFallback;
import org.springframework.context.annotation.Bean;

public class FeignConfiguration {
    @Bean
    public SystemAdminAPIFallback systemAdminAPIFallback() {
        return new SystemAdminAPIFallback();
    }
}
