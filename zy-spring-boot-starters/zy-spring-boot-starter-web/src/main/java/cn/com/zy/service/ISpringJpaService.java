package cn.com.zy.service;

import cn.com.zy.repository.SpringJpaRepository;
import cn.com.zy.util.QueryCondition;
import cn.com.zy.util.QueryConditionTypeEnum;
import cn.com.zy.util.SpringJpaPage;
import com.alibaba.fastjson.JSONArray;
import org.hibernate.query.criteria.internal.CriteriaBuilderImpl;
import org.hibernate.query.criteria.internal.CriteriaQueryImpl;
import org.hibernate.query.criteria.internal.OrderImpl;
import org.hibernate.query.criteria.internal.path.RootImpl;
import org.springframework.data.domain.Page;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public interface ISpringJpaService<T> {

    SpringJpaRepository<T,String> getBaseRepository();

    default T getById(String id){
        return this.getBaseRepository().getOne(id);
    }

    default List<T> list(SpringJpaPage<T> page){
        return this.getBaseRepository().findAll((root, query, builder) -> {
            jpaQueryConditionBuilder(page,(RootImpl<T>)root,(CriteriaQueryImpl)query,(CriteriaBuilderImpl)builder);
            return null;
        });
    }

    default SpringJpaPage<T> page(SpringJpaPage<T> page){
        Page result = this.getBaseRepository().findAll((root, criteriaQuery, criteriaBuilder) -> {
            jpaQueryConditionBuilder(page, (RootImpl<T>)root, (CriteriaQueryImpl)criteriaQuery, (CriteriaBuilderImpl)criteriaBuilder);
            return null;
        },page.getPageRequest());
        return SpringJpaPage.of(result);
    }



    default void jpaQueryConditionBuilder(SpringJpaPage<T> page, RootImpl<T> root, CriteriaQueryImpl query, CriteriaBuilderImpl builder) {
        if (page == null ) {
            return;
        }
        if (page.sorts != null) {
            List<Order> orders = new ArrayList<Order>();
            page.sorts.forEach(sort -> {
                jpaQuerySortBuilder(orders, sort, root);
            });
            query.orderBy(orders);
        }
        if (page.filters != null) {
            List<Predicate> predicates = new ArrayList<Predicate>();
            page.filters.forEach(filter -> {
                jpaQueryWhereBuilder(predicates, filter, (RootImpl<T>)root, builder);
            });
            query.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
        }
    }





    default void jpaQueryWhereBuilder(List<Predicate> predicates, QueryCondition filter, RootImpl<T> root, CriteriaBuilderImpl builder) {
//         默认条件为 "等值"
        QueryConditionTypeEnum type =  (filter.getType() == null) ? QueryConditionTypeEnum.EQ : filter.getType();
        Predicate predicate = null;
        Expression<String> field = builder.trim(root.get(filter.getField()));
        switch (type) {
            case EQ:
                predicate = builder.equal(field, filter.getValue());
                break;
            case EQ_IGNORE_CASE:
                predicate = builder.equal(builder.upper(field), toUpperCase(filter.getValue()));
                break;
            case NE:
                predicate = builder.notEqual(field, filter.getValue());
                break;
            case NE_IGNORE_CASE:
                predicate = builder.notEqual(builder.upper(field), toUpperCase(filter.getValue()));
                break;
            case GT:
                predicate = builder.greaterThan(field, filter.getValue());
                break;
            case GE:
                predicate = builder.greaterThanOrEqualTo(field, filter.getValue());
                break;
            case LT:
                predicate = builder.lessThan(field, filter.getValue());
                break;
            case LE:
                predicate = builder.lessThanOrEqualTo(field, filter.getValue());
                break;
            case BETWEEN:
                // 要求value是数组，长度为2,格式参考："[\"20\",\"30\"]"
                JSONArray array = JSONArray.parseArray(filter.getValue());
                predicate = builder.between(field, (String)array.get(0), (String)array.get(1));
                break;
            case NOT_BETWEEN:
                // 要求value同BETWEEN
                array = JSONArray.parseArray(filter.getValue());
                predicate = builder.between(field, (String)array.get(0), (String)array.get(1)).not();
                break;
            case LIKE:
                predicate = builder.like(field, filter.getValue(), getLikeKey());
                break;
            case LIKE_IGNORE_CASE:
                predicate = builder.like(builder.upper(field), toUpperCase(filter.getValue()), getLikeKey());
                break;
            case NOT_LIKE:
                predicate = builder.notLike(field, filter.getValue(), getLikeKey());
                break;
            case NOT_LIKE_IGNORE_CASE:
                predicate = builder.notLike(builder.upper(field), toUpperCase(filter.getValue()), getLikeKey());
                break;
            case LIKE_LEFT:
                predicate = builder.like(field, String.valueOf(getLikeKey()) + filter.getValue());
                break;
            case LIKE_LEFT_IGNORE_CASE:
                predicate = builder.like(builder.upper(field), String.valueOf(getLikeKey()) + toUpperCase(filter.getValue()));
                break;
            case NOT_LIKE_LEFT:
                predicate = builder.like(field, String.valueOf(getLikeKey()) + filter.getValue()).not();
                break;
            case NOT_LIKE_LEFT_IGNORE_CASE:
                predicate = builder.like(builder.upper(field), String.valueOf(getLikeKey()) + toUpperCase(filter.getValue())).not();
                break;
            case LIKE_RIGHT:
                predicate = builder.like(field, filter.getValue() + String.valueOf(getLikeKey()));
                break;
            case LIKE_RIGHT_IGNORE_CASE:
                predicate = builder.like(builder.upper(field), toUpperCase(filter.getValue()) + String.valueOf(getLikeKey()));
                break;
            case NOT_LIKE_RIGHT:
                predicate = builder.like(field, filter.getValue() + String.valueOf(getLikeKey())).not();
                break;
            case NOT_LIKE_RIGHT_IGNORE_CASE:
                predicate = builder.like(builder.upper(field), toUpperCase(filter.getValue()) + String.valueOf(getLikeKey())).not();
                break;
            case IS_NULL:
                predicate = builder.isNull(field);
                break;
            case IS_NOT_NULL:
                predicate = builder.isNotNull(field);
                break;
            case IN:
                array = JSONArray.parseArray(filter.getValue());
                predicate = builder.in(field, array);
                break;
            case NOT_IN:
                array = JSONArray.parseArray(filter.getValue());
                predicate = builder.in(field, array).not();
                break;
            case OR:
                predicate = builder.or();
                break;
            case AND:
                predicate = builder.and();
                break;
            default:
                predicate = null;
        }
        if(predicate != null) {
            predicates.add(predicate);
        }
    }

    default void jpaQuerySortBuilder(List<Order> orders, QueryCondition sort, RootImpl<T> root) {
        QueryConditionTypeEnum type =  (sort.getType() == null) ? QueryConditionTypeEnum.ORDER_BY_ASC : sort.getType();
        switch (type) {
            case ORDER_BY_ASC:
                JSONArray array = JSONArray.parseArray(sort.getField());
                orders.add(new OrderImpl(root.get(String.valueOf(array.get(0))), true));
                break;
            case ORDER_BY_DESC:
                array = JSONArray.parseArray(sort.getField());
                orders.add(new OrderImpl(root.get(String.valueOf(array.get(0))), false));
                break;
            default:
        }
    }

    default char getLikeKey() {
        return '%';
    }

    default String toUpperCase(String value) {
        if (value != null) {
            return value.toUpperCase();
        }
        return value;
    }

}
