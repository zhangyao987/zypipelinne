package cn.com.zy.service.impl;

import cn.com.zy.entity.MybatisEntity;
import cn.com.zy.mapper.MybatisBaseMapper;
import cn.com.zy.repository.SpringJpaRepository;
import cn.com.zy.service.ISpringJpaService;
import org.springframework.beans.factory.annotation.Autowired;

public class MybatisJpaServiceImpl<
        M extends MybatisBaseMapper<T>,
        R extends SpringJpaRepository<T, String>,
        T extends MybatisEntity>
        extends MybatisServiceImpl<M, T>
        implements ISpringJpaService<T> {
    @Autowired
    protected R baseRepository;
    @Override
    public SpringJpaRepository<T, String> getBaseRepository() {
        return this.baseRepository;
    }
}
