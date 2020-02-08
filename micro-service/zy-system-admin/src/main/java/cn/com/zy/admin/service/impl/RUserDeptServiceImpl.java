package cn.com.zy.admin.service.impl;

import cn.com.zy.admin.entity.RUserDept;
import cn.com.zy.admin.mapper.RUserDeptMapper;
import cn.com.zy.admin.repository.RUserDeptRepository;
import cn.com.zy.admin.service.IRUserDeptService;
import cn.com.zy.service.impl.MybatisJpaServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户-部门中间表 服务实现类
 * </p>
 *
 */
@Service
public class RUserDeptServiceImpl extends MybatisJpaServiceImpl<RUserDeptMapper, RUserDeptRepository, RUserDept> implements IRUserDeptService {

}
