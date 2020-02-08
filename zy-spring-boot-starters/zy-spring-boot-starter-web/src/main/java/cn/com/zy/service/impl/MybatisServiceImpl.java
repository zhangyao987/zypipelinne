package cn.com.zy.service.impl;

import cn.com.zy.entity.MybatisEntity;
import cn.com.zy.mapper.MybatisBaseMapper;
import cn.com.zy.service.IMybatisService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MybatisServiceImpl<M extends MybatisBaseMapper<T>, T extends MybatisEntity> extends ServiceImpl<M, T> implements IMybatisService<T> {

    @Value("${mybatis-plus.global-config.db-config.logic-delete-value:1}")
    protected Integer logicDeleteValue;
    @Value("${mybatis-plus.global-config.db-config.logic-not-delete-value:0}")
    protected Integer logicNotDeleteValue;

    @Override
    public boolean removeById(Serializable id) {
        T entity = null;
        try {
            entity = getEntityClass().newInstance();
            entity.setId((String)id);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return SqlHelper.delBool(this.baseMapper.deleteByIdWithFill(entity));
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean removeByMap(Map<String, Object> map) {
        Assert.notEmpty(map,"error map must not be empty", new Object[0]);
        UpdateWrapper uw = new UpdateWrapper();
        for (String entityFieldName : map.keySet()) {
            String dbFieldName = getEntityTableFieldValue(entityFieldName);
            uw.eq(dbFieldName, map.get(entityFieldName));
        }
        T entity = getEntityInstance();
        uw.set("DELETE",logicDeleteValue);
        return SqlHelper.delBool(this.baseMapper.update(entity,uw));
    }

    @Override
    public boolean resetByMap(List<T> entities, Map<String, Set<Object>> mappingInfo, int batchSize) {
        for (String entityFieldName : mappingInfo.keySet()) {
            String dbFieldName = getEntityTableFieldValue(entityFieldName);
            for (Object entityFieldValue : mappingInfo.get(entityFieldName)) {
                T entity = getEntityInstance();
                UpdateWrapper uw = new UpdateWrapper();
                uw.in(dbFieldName, entityFieldValue);
                uw.set("DELETED", logicDeleteValue);
                // 确保可记录最秀修改人与修改事件
                this.baseMapper.update(entity, uw);
            }
        }
        // 以下代码借鉴了父类 ServiceImpl asveBatch方法
        for(T entity : entities) {
            this.baseMapper.insert(entity);
        }
        return true;
    }
}
