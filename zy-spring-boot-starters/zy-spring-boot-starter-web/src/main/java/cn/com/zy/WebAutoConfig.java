package cn.com.zy;

import cn.com.zy.util.QueryConditionTypeEnum;
import cn.com.zy.util.api.ApiResultCode;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @description: <p>通用bean组件配置</p>
 */
@Import({ GlobalExceptionHandler.class })
@Configuration
public class WebAutoConfig {
    /**
     * @description: <p>FastJson整合</p>
     * @return: org.springframework.boot.autoconfigure.http.HttpMessageConverters
     */
    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig config = new FastJsonConfig();

        config.setSerializerFeatures(SerializerFeature.WriteEnumUsingToString, SerializerFeature.WriteNullBooleanAsFalse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullNumberAsZero, SerializerFeature.WriteNullStringAsEmpty);

        // 便于动态SQL 枚举映射
        SerializeConfig serializeConfig = new SerializeConfig();
        for (QueryConditionTypeEnum item : QueryConditionTypeEnum.values()) {
            serializeConfig.configEnumAsJavaBean(item.getClass());
        }
        for (ApiResultCode item : ApiResultCode.values()) {
            serializeConfig.configEnumAsJavaBean(item.getClass());
        }
        config.setSerializeConfig(serializeConfig);

        fastConverter.setFastJsonConfig(config);
        return new HttpMessageConverters(fastConverter);
    }

}
