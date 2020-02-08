package cn.com.zy.util;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @Description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SpringJpaPage<T> extends AbstractPage<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    public SpringJpaPage() {
        this.total = 0L;
        this.pageSize = 10L;
        this.page = 1L;
        this.list = new ArrayList<T>();
        this.filters = new ArrayList<QueryCondition>();
        this.sorts = new ArrayList<QueryCondition>();
    }

    public SpringJpaPage(Page page) {
        this.total = page.getTotalElements();
        this.pageSize = page.getSize();
        this.page = page.getNumber() + 1;
        this.list = page.getContent();
    }

    public SpringJpaPage(SpringJpaPage page) {
        this.total = page.getTotal();
        this.pageSize = page.getPageSize();
        this.page = page.getPage();
        this.list = page.getList();
    }

    public static SpringJpaPage of(Page page) {
        return new SpringJpaPage(page);
    }

    public static SpringJpaPage of(SpringJpaPage page) {
        return new SpringJpaPage(page);
    }

    public static SpringJpaPage fromParams(SpringJpaPage params) {
        SpringJpaPage page = new SpringJpaPage();
        page.setTotal(params.getTotal())
                .setPageSize(params.getPageSize())
                .setPage(params.getPage())
                .setFilters(params.getFilters())
                .setSorts(params.getSorts());
        return page;
    }

    @JSONField(serialize=false)
    public PageRequest getPageRequest() {
        return PageRequest.of((int)this.getPage() - 1, (int)this.getPageSize());
    }

}
