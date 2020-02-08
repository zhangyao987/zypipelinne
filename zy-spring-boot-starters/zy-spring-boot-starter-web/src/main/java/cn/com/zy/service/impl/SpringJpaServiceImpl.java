package cn.com.zy.service.impl;

import cn.com.zy.repository.SpringJpaRepository;
import cn.com.zy.service.ISpringJpaService;
import org.springframework.beans.factory.annotation.Autowired;

public class SpringJpaServiceImpl<R extends SpringJpaRepository<T,String>, T> implements ISpringJpaService<T> {

    @Autowired
    protected R baseRepostiory;

    @Override
    public SpringJpaRepository<T, String> getBaseRepository() {
        return this.baseRepostiory;
    }
}
