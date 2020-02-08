package cn.com.zy.sentinel;

import com.alibaba.csp.sentinel.adapter.servlet.callback.WebCallbackManager;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SentinelAutoConfig {
    public SentinelAutoConfig() {
        WebCallbackManager.setUrlBlockHandler(new SentinelUrlBlockHandler());
    }
}
