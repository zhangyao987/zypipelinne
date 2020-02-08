package cn.com.zy.ribbon.properties;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "zy.ribbon")
@Getter
@Setter
public class RibbonProperties {
}
