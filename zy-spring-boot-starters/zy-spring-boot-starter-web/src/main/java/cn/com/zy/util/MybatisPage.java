package cn.com.zy.util;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: <p>分页信息封装，搭配前端使用</p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MybatisPage<T> extends AbstractPage<T> implements IPage<T>, Serializable {

    private static final long serialVersionUID = 1L;

    public MybatisPage() {
        this.total = 0L;
        this.pageSize = 10L;
        this.page = 1L;
        this.list = new ArrayList<T>();
        this.filters = new ArrayList<QueryCondition>();
        this.sorts = new ArrayList<QueryCondition>();

    }

    @Override
    public long getTotal() {
        return this.total;
    }

    @Override
    public MybatisPage<T> setTotal(long total) {
        this.total = total;
        return this;
    }

    @Override
    @JSONField(serialize=false)
    public long getSize() {
        return this.pageSize;
    }

    @Override
    public MybatisPage<T> setSize(long pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    @Override
    @JSONField(serialize=false)
    public long getCurrent() {
        return this.page;
    }

    @Override
    public MybatisPage<T> setCurrent(long current) {
        this.page = current;
        return this;
    }

    @Override
    @JSONField(serialize=false)
    public List<T> getRecords() {
        return this.list;
    }

    @Override
    public MybatisPage<T> setRecords(List<T> list) {
        this.list = list;
        return this;
    }

    @Override
    @JSONField(serialize=false)
    public long getPages() {
        if (this.getSize() == 0L) {
            return 0L;
        } else {
            long pages = this.getTotal() / this.getSize();
            if (this.getTotal() % this.getSize() != 0L) {
                ++pages;
            }

            return pages;
        }
    }

    @Override
    @JSONField(serialize=false)
    public boolean isSearchCount() {
        return true;
    }

    @JSONField(serialize=false)
    public static MybatisPage<Object> of(IPage page) {
        MybatisPage<Object> convert = new MybatisPage<Object>();
        convert.setTotal(page.getTotal()).setPage(page.getCurrent()).setPageSize(page.getSize());
        return convert;
    }
}
