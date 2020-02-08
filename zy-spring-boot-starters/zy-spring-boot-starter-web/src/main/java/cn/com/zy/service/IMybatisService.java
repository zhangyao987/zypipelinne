package cn.com.zy.service;

import cn.com.zy.entity.MybatisEntity;
import cn.com.zy.util.MybatisPage;
import cn.com.zy.util.QueryCondition;
import cn.com.zy.util.QueryConditionTypeEnum;
import cn.com.zy.util.exceptions.ServiceHandlerGlobalException;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public interface IMybatisService<T extends MybatisEntity> extends IService<T> {
    /**
     * @description: <p>根据ID删除，支持记录最后修改人，反射获取Entity实例，通过deleteByIdWithFill删除</p>
     *               <p>覆盖父类方法</p>
     * @param: id
     * @return: boolean
     */
    @Override
    public boolean removeById(Serializable id);
    /**
     * @description: <p>删除，该方法将会通过反射手段将entity属性名称转换为数据库属性名称</p>
     *               <p>覆盖父类方法</p>
     * @param: map，key值为entity的属性名称，value
     * @return: boolean
     */
    @Override
    public boolean removeByMap(Map<String, Object> map);
    /**
     * @description: <p>重新构建映射关系，清除原有映射关系，只保留本次新增的映射关系,默认批量操作大小设置为1000</p>
     * @param: entities 新的实体映射关系
     * @param: mappingInfo <p>实体属性名称键值对，独立生效</p>
     *                      <p>如：[{'roleId': ['1','2']}, {'userId': ['3']}],将先根据roleId = 1删除,然后根据roleId = 2删除，并再次根据userId = 3进行删除</p>
     *                      <p>另：key值传递实体属性名称即可，将自动根据实体注解获取对应的数据库字段名称</p>
     * @return: boolean
     */
    default boolean resetByMap(List<T> entities, Map<String, Set<Object>> mappingInfo) {
        return this.resetByMap(entities, mappingInfo, 1000);
    }

    default List<T> list(MybatisPage<T> page) {
        QueryWrapper<T> queryWrapper = mybatisQueryWrapperBuilder(page);
        return this.list(queryWrapper);
    }

    default IPage<T> page(MybatisPage<T> page) {
        QueryWrapper<T> queryWrapper = mybatisQueryWrapperBuilder(page);
        return this.page(page, queryWrapper);
    }
    //==================================以下均为工具类方法===========================================
    /**
     * @description: <p>重新构建映射关系，清除原有映射关系，只保留本次新增的映射关系</p>
     * @param: entities 新的实体映射关系
     * @param: mappingInfo <p>实体属性名称键值对，独立生效</p>
     *                      <p>如：[{'roleId': ['1','2']}, {'userId': ['3']}],将先根据roleId = 1删除,然后根据roleId = 2删除，并再次根据userId = 3进行删除</p>
     *                      <p>另：key值传递实体属性名称即可，将自动根据实体注解获取对应的数据库字段名称</p>
     * @param: <p>batchSize 设定批量新增的大小，过大可能存在性能问题</p>
     * @return: boolean
     */
    boolean resetByMap(List<T> entities, Map<String, Set<Object>> mappingInfo, int batchSize);
    /**
     * @description: <p>根据动态查询条件，查询符合条件的map数据</p>
     * @param: Wrapper<T> queryWrapper
     * @return: 返回符合条件的Map数据，其中key为id，value为实体对象结构
     */
    default Map<String, T> mapIdEntity(Wrapper<T> queryWapper){
        Map<String, T> results = new HashMap<>();
        this.getBaseMapper().selectList(queryWapper).stream().filter(e ->{
            results.put(e.getId(), e);
            return true;
        }).collect(Collectors.toList());
        return results;
    }
    /**
     * @description: <p>根据动态查询条件，查询符合条件的map数据</p>
     * @param: Wrapper<T> queryWrapper
     * @return: 返回符合条件的Map数据，其中key为id，value为实体对象map结构
     */
    default Map<String, Map<String, Object>> mapIdEntityMap(Wrapper<T> queryWrapper){
        Map<String, Map<String, Object>> results = new HashMap<>();
        this.getBaseMapper().selectMaps(queryWrapper).stream().filter(e ->{
            results.put((String)e.get("id"),e);
            return true;
        }).collect(Collectors.toList());
        return results;
    }
    /**
     * @description: <p>根据分页参数构建QueryWrapper</p>
     * @param: MybatisPage<T> page
     * @return: QueryWrapper<T> queryWrapper
     */
    default QueryWrapper<T> mybatisQueryWrapperBuilder( MybatisPage<T> page) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<T>();
        if (page != null && page.getFilters() != null) {
            for(QueryCondition filter : page.getFilters()) {
                mybatisQueryWhereBuilder(queryWrapper, filter);
            }
        }
        if (page != null && page.getSorts() != null) {
            for(QueryCondition sort : page.getSorts()) {
                mybatisQuerySortBuilder(queryWrapper, sort);
            }
        }
        return queryWrapper;
    }

    /**
     * @description: <p>反射获取信息的Entity实例</p>
     * @param:
     * @return:
     */
    default T getEntityInstance() {
        try {
            return getEntityClass().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * @description: <p>获取当前Service绑定的的Entity Class对象</p>
     *               <p>缺确保Entity必须是最后一项</p>
     * @return: Class<T>
     */
    default Class<T> getEntityClass() {
        ParameterizedType parameterizedType = (ParameterizedType)this.getClass().getGenericSuperclass();
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        int i = 0;
        for (i = 0; i < actualTypeArguments.length; i ++) {
            if (MybatisEntity.class.isAssignableFrom((Class)actualTypeArguments[i])) {
                break;
            }
        }
        if (i == actualTypeArguments.length) {
            throw new RuntimeException("未获取到继承自MybatisEntity的实体");
        }
        return (Class <T>) actualTypeArguments[i];
    }

    /**
     * <p>获取Entity字段上@TableField 注解描述的数据库字段名称（支持一级父类）</p>
     * @param entityClass
     * @param entityFieldName
     * @return 数据库字段名称
     */
    default String getEntityTableFieldValue(Class<T> entityClass, String entityFieldName) {
        String dbFieldName = null;
        try {
            dbFieldName = entityClass.getDeclaredField(entityFieldName).getAnnotation(TableField.class).value();
        } catch (NoSuchFieldException e1) {
            dbFieldName = null;
            try {
                Field field = entityClass.getSuperclass().getDeclaredField(entityFieldName);
                TableField a1 = field.getAnnotation(TableField.class);
                if (a1 != null) {
                    dbFieldName = a1.value();
                    return dbFieldName;
                }
                TableId a2 = field.getAnnotation(TableId.class);
                if (a2 != null) {
                    dbFieldName = a2.value();
                    return dbFieldName;
                }
                return dbFieldName;
            } catch (NoSuchFieldException e2) {
                dbFieldName = null;
                throw new ServiceHandlerGlobalException("非法的参数");
            }
        }
        return dbFieldName;
    }

    /**
     * @description: <p>获取Entity字段上@TableField 注解描述的数据库字段名称（支持一级父类）</p>
     * @param:
     * @return:
     */
    default String getEntityTableFieldValue(String entityFieldName) {
        return getEntityTableFieldValue(getEntityClass(), entityFieldName);
    }
    /**
     * @description: <p>封装where条件</p>
     * @param: QueryWrapper<T> queryWrapper
     * @param: QueryCondition filter
     * @return: void
     */
    default void mybatisQueryWhereBuilder(QueryWrapper<T> queryWrapper, QueryCondition filter) {
        // 默认条件为 "等值"
        QueryConditionTypeEnum type =  (filter.getType() == null) ? QueryConditionTypeEnum.EQ : filter.getType();
        switch (type) {
            case EQ:
                queryWrapper.eq(getEntityTableFieldValue(filter.getField()), filter.getValue());
                break;
            case NE:
                queryWrapper.ne(getEntityTableFieldValue(filter.getField()), filter.getValue());
                break;
            case GT:
                queryWrapper.gt(getEntityTableFieldValue(filter.getField()), filter.getValue());
                break;
            case GE:
                queryWrapper.ge(getEntityTableFieldValue(filter.getField()), filter.getValue());
                break;
            case LT:
                queryWrapper.lt(getEntityTableFieldValue(filter.getField()), filter.getValue());
                break;
            case LE:
                queryWrapper.le(getEntityTableFieldValue(filter.getField()), filter.getValue());
                break;
            case BETWEEN:
                // 要求value是数组，长度为2,格式参考："[\"20\",\"30\"]"
                JSONArray array = JSONArray.parseArray(filter.getValue());
                queryWrapper.between(getEntityTableFieldValue(filter.getField()), array.get(0), array.get(0));
                break;
            case NOT_BETWEEN:
                // 要求value同BETWEEN
                array = JSONArray.parseArray(filter.getValue());
                queryWrapper.between(getEntityTableFieldValue(filter.getField()), array.get(0), array.get(0));
                break;
            case LIKE:
                queryWrapper.like(getEntityTableFieldValue(filter.getField()), filter.getValue());
                break;
            case NOT_LIKE:
                queryWrapper.notLike(getEntityTableFieldValue(filter.getField()), filter.getValue());
                break;
            case LIKE_LEFT:
                queryWrapper.likeLeft(getEntityTableFieldValue(filter.getField()), filter.getValue());
                break;
            case LIKE_RIGHT:
                queryWrapper.likeRight(getEntityTableFieldValue(filter.getField()), filter.getValue());
                break;
            case IS_NULL:
                queryWrapper.isNull(getEntityTableFieldValue(filter.getField()));
                break;
            case IS_NOT_NULL:
                queryWrapper.isNotNull(getEntityTableFieldValue(filter.getField()));
                break;
            case IN:
                array = JSONArray.parseArray(filter.getValue());
                queryWrapper.in(getEntityTableFieldValue(filter.getField()), array);
                break;
            case NOT_IN:
                array = JSONArray.parseArray(filter.getValue());
                queryWrapper.notIn(getEntityTableFieldValue(filter.getField()), array);
                break;
            case IN_SQL:
                // 要求value是字符串，格式有两种，实际的逗号分开的值，或可执行的sql语句，如："1，2，3，4，5" 或 "SELECT ID FROM T_DEPT WHERE NAME IS NOT NULL"
                queryWrapper.inSql(getEntityTableFieldValue(filter.getField()), filter.getValue());
                break;
            case NOT_IN_SQL:
                // 要求value同IN_SQL
                queryWrapper.notInSql(getEntityTableFieldValue(filter.getField()), filter.getValue());
                break;
            case OR:
                queryWrapper.or();
                break;
            case AND:
                queryWrapper.and(wrapper -> {
                    for(QueryCondition f : filter.getFilters()) {
                        mybatisQueryWhereBuilder(wrapper, f);
                    }
                    return wrapper;
                });
                break;
            case NESTED:
                queryWrapper.nested(wrapper -> {
                    for(QueryCondition f : filter.getFilters()) {
                        mybatisQueryWhereBuilder(wrapper, f);
                    }
                    return wrapper;
                });
                break;
            default:

        }
    }

    /**
     * @description: <p>封装sort排序规则</p>
     * @param: QueryWrapper<T> queryWrapper
     * @param: QueryCondition sort
     * @return: void
     */
    default void mybatisQuerySortBuilder(QueryWrapper<T> queryWrapper, QueryCondition sort) {
        // 默认条件为 "等值"
        QueryConditionTypeEnum type =  (sort.getType() == null) ? QueryConditionTypeEnum.ORDER_BY_ASC : sort.getType();
        switch (type) {
            case ORDER_BY_ASC:
                // 要求field为JSON数组，如实参考如："[\"age\",\"name\"]"
                JSONArray array = JSONArray.parseArray(sort.getField());
                String[] dbFields = new String[array.size()];
                int i = 0;
                for (Object o : array) {
                    dbFields[i] = getEntityTableFieldValue((String)o);
                    i ++;
                }
                queryWrapper.orderByAsc(dbFields);
                break;
            case ORDER_BY_DESC:
                // 要求field同ORDER_BY_ASC
                array = JSONArray.parseArray(sort.getField());
                dbFields = new String[array.size()];
                i = 0;
                for (Object o : array) {
                    dbFields[i] = getEntityTableFieldValue((String)o);
                    i ++;
                }
                queryWrapper.orderByDesc(dbFields);
                break;
            default:

        }
    }



}
