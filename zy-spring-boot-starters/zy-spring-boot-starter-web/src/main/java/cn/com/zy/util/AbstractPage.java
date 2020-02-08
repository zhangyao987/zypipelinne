package cn.com.zy.util;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public abstract class AbstractPage<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    public long total;
    public long pageSize;
    public long page;
    public List<T> list;

    @JSONField(serialize=false)
    public List<QueryCondition> filters;

    @JSONField(serialize=false)
    public List<QueryCondition> sorts;

    @JSONField(serialize=false)
    public T entity;

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

}
