package cn.com.zy.controller;

import cn.com.zy.entity.MybatisEntity;
import cn.com.zy.params.ResetRTableParam;
import cn.com.zy.service.IMybatisService;
import cn.com.zy.util.api.ApiResult;
import cn.com.zy.util.api.ApiResultWrapper;
import cn.com.zy.util.exceptions.ControllerFieldCheckException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class AbstractMybatisRController<S extends IMybatisService<T>, T extends MybatisEntity> {

    /**
     * @description: <p>请务必在子类中填充完整的映射字段</p>
     *               <p>如：用户部门中间表，则需要填充上'userId'和'deptId'</p>
     */
    public Set<String> mappingFields = new HashSet<String>();

    @Autowired
    protected S baseService;

    /**
     * @description: <p>单个新增，仅追加</p>
     * @param: request
     * @param: entity
     * @return: ApiResult，数据结构满足开发规范
     */
    @PostMapping(value = "add", name="单个新增映射关系")
    public ApiResult add(HttpServletRequest request, T entity){
        checkMappingFields(entity);
        baseService.save(entity);
        return ApiResultWrapper.success();
    }
    /**
     * @description: <p>批量重置，清除原有映射关系</p>
     *               <p>支持根据映射关系，自由设定重置关系，如R_ROLE_MENU表</p>
     *               <p>1、单向重置：清空某特定角色，与menu的关系，mappingFields中只需要传递字符串'roleId'</p>
     *               <p>2、多向重置（慎用）：同时向清空菜单对应的角色信息，mappingFields中传递字符串'roleId'和'menuId'，此时，将表现为双向重置</p>
     * @param: request
     * @param: entities
     * @return: ApiResult，数据结构满足开发规范
     */
    @PostMapping(value = "reset", name="批量重置映射关系")
    public ApiResult resetAdd(HttpServletRequest request, @RequestBody ResetRTableParam<T> resetParam){
        Map<String, Set<Object>> mappingInfo = new HashMap<String, Set<Object>>();
        for (T entity : resetParam.getEntities()) {
            for (String mappingField: resetParam.getMappingFields()) {
                if (mappingInfo.get(mappingField) == null) {
                    mappingInfo.put(mappingField, new HashSet<Object>());
                }
                mappingInfo.get(mappingField).add(getEntityFieldValue(entity, mappingField));
            }
        }
        baseService.resetByMap(resetParam.getEntities(), mappingInfo);
        return ApiResultWrapper.success();
    }

    /**
     * @description: <p>根据映射关系删除</p>
     * @param: request
     * @param: mapping
     * @return: ApiResult，数据结构满足开发规范
     */
    @PostMapping(value = "del-by-entity-mapping", name="根据映射键值对删除映射关系")
    public ApiResult delByEntityMapping(HttpServletRequest request, T entity){
        Map<String, Object> mapping = new HashMap<String, Object>();
        for (String mappingField: mappingFields) {
            mapping.put(mappingField, getEntityFieldValue(entity, mappingField));
        }
        baseService.removeByMap(mapping);
        return ApiResultWrapper.success();
    }

    /**
     * @description: <p>中间表关联字段检查是否缺失</p>
     *               <p>检查mappingFields是否在子类被初始化，已经对应中间表实体中是否已经有值，不允许为null</p>
     * @param: entity中间表实体
     * @return: void
     */
    protected void checkMappingFields(T entity) {
        if (this.mappingFields.size() == 0) {
            throw new ControllerFieldCheckException("请配置中间表字段映射");
        }
        for (String mappingField: mappingFields) {
            getEntityFieldValue(entity, mappingField);
        }
    }

    /**
     * @description: <p>根据反射获取指定字段值，依赖于get方法</p>
     * @param: entity 中间表实体
     * @param: entityFieldName 中间表实体属性名称
     * @return: 返回entityFieldName字段值
     */
    protected Object getEntityFieldValue(T entity, String entityFieldName) {
        Object value = null;
        try {
            Method getMethod = entity.getClass().getDeclaredMethod("get" + entityFieldName.substring(0, 1).toUpperCase() + entityFieldName.substring(1));
            value = getMethod.invoke(entity);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            value = null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            value = null;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            value = null;
        }
        if (value == null) {
            throw new ControllerFieldCheckException("无效的mapping映射属性或其值为null");
        }
        return value;
    }
}
