package cn.com.zy.gateway.feign.fallback;

import cn.com.zy.gateway.feign.SystemAdminAPI;
import cn.com.zy.util.api.ApiResult;
import cn.com.zy.util.api.ApiResultWrapper;
import cn.com.zy.util.enums.MicroService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SystemAdminAPIFallback implements FallbackFactory<SystemAdminAPI> {

    @Override
    public SystemAdminAPI create(Throwable throwable) {
        return new SystemAdminAPI() {

            @Override
            public ApiResult<JSONArray> getUserPermissions() {
                log.error(String.format("服务[%s]调用错误，错误信息：\n%s", MicroService.SYSTEM_ADMIN.name, throwable.getMessage()));
                return ApiResultWrapper.unknown();
            }

            @Override
            public ApiResult<JSONObject> getAllPlatformPermissions() {
                log.error(String.format("服务[%s]调用错误，错误信息：\n%s", MicroService.SYSTEM_ADMIN.name, throwable.getMessage()));
                return ApiResultWrapper.unknown();
            }

            @Override
            public ApiResult<Boolean> verifyStaticToken(String tokenId) {
                log.error(String.format("服务[%s]调用错误，错误信息：\n%s", MicroService.SYSTEM_ADMIN.name, throwable.getMessage()));
                return ApiResultWrapper.unknown();
            }
        };
    }
}
