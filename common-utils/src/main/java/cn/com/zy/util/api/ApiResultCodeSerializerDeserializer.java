package cn.com.zy.util.api;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;

import java.io.IOException;
import java.lang.reflect.Type;

public class ApiResultCodeSerializerDeserializer implements ObjectDeserializer, ObjectSerializer {

    @Override
    public ApiResultCode deserialze(DefaultJSONParser parser, Type type, Object o) {
        String value = (String)parser.parse();
        return ApiResultCode.deserializer(value);
    }

    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        if (object == null) {
            serializer.out.writeNull();
        } else {
            serializer.out.writeString(((ApiResultCode)object).getKey());
        }
    }

    @Override
    public int getFastMatchToken() {
        return 0;
    }
}
