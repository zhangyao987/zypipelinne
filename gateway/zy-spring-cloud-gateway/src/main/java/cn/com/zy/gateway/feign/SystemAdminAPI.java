package cn.com.zy.gateway.feign;


import cn.com.zy.gateway.feign.fallback.SystemAdminAPIFallback;
import cn.com.zy.util.api.ApiResult;
import cn.com.zy.util.api.ApiResultCode;
import cn.com.zy.util.constatns.MicroServiceConstatns;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@FeignClient(name = MicroServiceConstatns.SYSTEM_ADMIN, fallbackFactory = SystemAdminAPIFallback.class, configuration = FeignConfiguration.class)
@RequestMapping(value = "/admin")
public interface SystemAdminAPI {

    @GetMapping(value = "auth/user-permissions")
    ApiResult<JSONArray> getUserPermissions();

    @GetMapping(value = "auth/all-platform-permissions")
    ApiResult<JSONObject> getAllPlatformPermissions();

    @GetMapping(value = "auth/verify-static-token")
    ApiResult<Boolean> verifyStaticToken(@RequestParam("tokenId") String tokenId);

    default Boolean verifyStaticTokenHandle(String tokenId) {
        return Optional.of(this.verifyStaticToken(tokenId))
                .filter(i -> ApiResultCode.SUCCESS.equals(i.getCode()))
                .orElseThrow(() -> new RuntimeException("feign 接口【/admin/auth/verify-static-token】调用结果集显示接口运行失败"))
                .getData();
    }
}
