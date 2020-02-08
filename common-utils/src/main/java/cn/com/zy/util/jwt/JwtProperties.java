package cn.com.zy.util.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "zy.jwt")
@Getter
@Setter
public class JwtProperties {

    String[] excludePathPatterns ={"/admin/auth/captcha", "/admin/auth/login"};
    /**
     * 服务层 token 秘钥
     */
    String serviceSecret ="zy.cn";
    /**
     * 网关层 token 秘钥
     */
    String gatewaySecret = "zy.cn";
    /**
     * token 有效时间
     */
    Integer minutes = 30;

}
