package cn.com.zy.util;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;

import java.io.IOException;
import java.lang.reflect.Type;

public class QueryConditionTypeEnumSerializerDeserializer implements ObjectDeserializer, ObjectSerializer {
    @Override
    public QueryConditionTypeEnum deserialze(DefaultJSONParser parser, Type type, Object o) {
        String value = (String)parser.parse();
        return QueryConditionTypeEnum.deserializer(value);
    }

    @Override
    public int getFastMatchToken() {
        return 0;
    }

    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        if (object == null) {
            serializer.out.writeNull();
        } else {
            serializer.out.writeString(((QueryConditionTypeEnum)object).name());
        }
    }
}
