package cn.com.zy.util.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * @Description:
 */
public enum JwtIssuer {
    SYSTEM_LOGIN("系统登录"),FEIGN_TOKEN("feign TOKEN"),STATIC_TOKEN("静态TOKEN");

    @Setter
    @Getter
    private String value;

    JwtIssuer(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.name();
    }
}
