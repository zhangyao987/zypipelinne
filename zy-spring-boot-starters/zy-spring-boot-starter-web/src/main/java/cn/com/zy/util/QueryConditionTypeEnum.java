package cn.com.zy.util;

import com.alibaba.fastjson.annotation.JSONType;

/**
 * @description: 面向mybaties-plus的QueryWrapper查询条件类型
 */

@JSONType(serializer = QueryConditionTypeEnumSerializerDeserializer.class, deserializer = QueryConditionTypeEnumSerializerDeserializer.class)
public enum QueryConditionTypeEnum {

    /**
     * @description:
     */
    EQ,

    /**
     * @description:
     */
    EQ_IGNORE_CASE,

    /**
     * @description:
     */
    NE,

    /**
     * @description:
     */
    NE_IGNORE_CASE,

    /**
     * @description:
     */
    GT,

    /**
     * @description:
     */
    GE,

    /**
     * @description:
     */
    LT,

    /**
     * @description:
     */
    LE,

    /**
     * @description:
     */
    BETWEEN,

    /**
     * @description:
     */
    NOT_BETWEEN,

    /**
     * @description:
     */
    LIKE,

    /**
     * @description:
     */
    LIKE_IGNORE_CASE,

    /**
     * @description:
     */
    NOT_LIKE,

    /**
     * @description:
     */
    NOT_LIKE_IGNORE_CASE,

    /**
     * @description:
     */
    LIKE_LEFT,

    /**
     * @description:
     */
    LIKE_LEFT_IGNORE_CASE,

    /**
     * @description:
     */
    NOT_LIKE_LEFT,

    /**
     * @description:
     */
    NOT_LIKE_LEFT_IGNORE_CASE,

    /**
     * @description:
     */
    LIKE_RIGHT,

    /**
     * @description:
     */
    LIKE_RIGHT_IGNORE_CASE,

    /**
     * @description:
     */
    NOT_LIKE_RIGHT,

    /**
     * @description:
     */
    NOT_LIKE_RIGHT_IGNORE_CASE,

    /**
     * @description:
     */
    IS_NULL,

    /**
     * @description:
     */
    IS_NOT_NULL,

    /**
     * @description:
     */
    IN,

    /**
     * @description:
     */
    NOT_IN,

    /**
     * @description:
     */
    IN_SQL,

    /**
     * @description:
     */
    NOT_IN_SQL,

    /**
     * @description:
     */
    GROUP_BY,

    /**
     * @description:
     */
    ORDER_BY_ASC,

    /**
     * @description:
     */
    ORDER_BY_DESC,

    /**
     * @description:
     */
    OR,

    /**
     * @description:
     */
    AND,

    /**
     * @description:
     */
    NESTED;

    public static QueryConditionTypeEnum deserializer(String name) {
        QueryConditionTypeEnum result = null;
        for (int i=0; i<QueryConditionTypeEnum.values().length; i++) {
            if (QueryConditionTypeEnum.values()[i].name().equals(name)) {
                result = QueryConditionTypeEnum.values()[i];
            }
        }
        return result;
    }

}
