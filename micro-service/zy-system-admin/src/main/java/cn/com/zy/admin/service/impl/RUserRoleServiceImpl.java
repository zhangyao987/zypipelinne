package cn.com.zy.admin.service.impl;

import cn.com.zy.admin.entity.RUserRole;
import cn.com.zy.admin.mapper.RUserRoleMapper;
import cn.com.zy.admin.repository.RUserRoleRepository;
import cn.com.zy.admin.service.IRUserRoleService;
import cn.com.zy.service.impl.MybatisJpaServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户-角色中间表 服务实现类
 * </p>
 *
 */
@Service
public class RUserRoleServiceImpl extends MybatisJpaServiceImpl<RUserRoleMapper, RUserRoleRepository, RUserRole> implements IRUserRoleService {

}
