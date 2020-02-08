package cn.com.zy.feign;

import cn.com.zy.util.context.UserContextHolder;
import cn.com.zy.util.jwt.JwtUtils;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import feign.Logger;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Calendar;
import java.util.Date;

/**
 * @description: <p>Feign配置</p>
 */
@Configuration
@Slf4j
public class FeignAutoConfigure {

    @Value("${zy.jwt.service-secret}")
    private String secret;

    public FeignAutoConfigure() {
        log.debug("程序入口处，需要添加 @EnableFeignClients(basePackages = \"cn.com.zy.*.feign\"，已添加，可忽略)");
    }

    /**
     * @description: <p>Feign 日志级别</p>
     */
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    /**
     * @description: <p>创建Feign请求拦截器，在发送请求前设置认证的token,各个微服务将token设置到环境变量中来达到通用</p>
     */
    @Bean
    public FeignTokenRequestInterceptor basicAuthRequestInterceptor() {
        return new FeignTokenRequestInterceptor();
    }

    public class FeignTokenRequestInterceptor  implements RequestInterceptor {

        public FeignTokenRequestInterceptor() { }

        @Override
        public void apply(RequestTemplate template) {
            String token = UserContextHolder.getJwtToken();
            template.header(JwtUtils.HEAD_TOKEN_KEY, token);
            if (token == null && template.headers().containsKey(JwtUtils.SPECIAL_REQUEST_HEADED_KEY)) {
                // 计划任务、服务启动等由系统内部触发fegin调用，动态内置一个token
                // 不要写入redis，防止外泄，严格控制有效时间
                Date now = new Date();
                Calendar cal = Calendar.getInstance();
                cal.setTime(now);
                cal.add(Calendar.MINUTE, 5);
                token = JWT.create()
                        // 编号, 保持与用户ID相同
                        .withJWTId("fegin")
                        // Redis KEY使用，用户ID开头，Redis过期时间为最终有效的时间
                        .withKeyId("fegin")
                        // 签发时间
                        .withIssuedAt(now)
                        // 签发人
                        .withIssuer("cn.com.taiji.feign.FeignTokenRequestInterceptor")
                        // 过期时间
                        .withExpiresAt(cal.getTime())
                        .withNotBefore(now)
                        .withSubject(null)
                        .sign(Algorithm.HMAC256(secret));
                template.header(JwtUtils.HEAD_TOKEN_KEY, token);
            }
        }
    }
    // TODO 目前没有通用ResponseInterceptor

}
