package cn.com.zy.util;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @description: <p>面向mybaties-plus的QueryWrapper查询条件</p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class QueryCondition {
    /**
     * @description <p>条件类型，默认为等值条件</p>
     */
    private QueryConditionTypeEnum type;
    /**
     * @description <p>entity实体字段名称，利用反射实现自动转换为对应的数据库字段名称</p>
     */
    private String field;
    /**
     * @description <p>field取值，依据type类型，实际的格式存在差异，前端需要处理成字符串统一交给后端处理</p>
     */
    private String value;
    /**
     * @description  <p>嵌套条件,需要注意的是，嵌套使用是有局限的，请按照SQL标准使用嵌套</p>
     */
    private List<QueryCondition> filters;


    public QueryCondition() {}

    public QueryCondition(String field, String value) {
        this.field = field;
        this.value = value;
    }

    public QueryCondition(QueryConditionTypeEnum type, String field, String value) {
        this.type = type;
        this.field = field;
        this.value = value;
    }

}
