package cn.com.zy.util.api;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResult<T> {

    private ApiResultCode code;

    private String message;

    private T data;

    public ApiResult() {}

    public ApiResult(ApiResultCode code, String message) {
        this.code = code;
        this.message = message;
    }

    public ApiResult(ApiResultCode code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public String toJSONString() {
        return JSONObject.toJSONString(this, SerializerFeature.WriteEnumUsingToString);
    }


}
