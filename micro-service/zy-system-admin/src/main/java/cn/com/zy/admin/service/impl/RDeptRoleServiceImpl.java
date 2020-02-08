package cn.com.zy.admin.service.impl;

import cn.com.zy.admin.entity.RDeptRole;
import cn.com.zy.admin.mapper.RDeptRoleMapper;
import cn.com.zy.admin.repository.RDeptRoleRepository;
import cn.com.zy.admin.service.IRDeptRoleService;
import cn.com.zy.service.impl.MybatisJpaServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 部门-角色，中间表 服务实现类
 * </p>
 *
 */
@Service
public class RDeptRoleServiceImpl extends MybatisJpaServiceImpl<RDeptRoleMapper, RDeptRoleRepository, RDeptRole> implements IRDeptRoleService {

}
