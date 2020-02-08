package cn.com.zy.admin.service.impl;

import cn.com.zy.admin.entity.RUserGroupRoleGroup;
import cn.com.zy.admin.mapper.RUserGroupRoleGroupMapper;
import cn.com.zy.admin.repository.RUserGroupRoleGroupRepository;
import cn.com.zy.admin.service.IRUserGroupRoleGroupService;
import cn.com.zy.service.impl.MybatisJpaServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户分组-角色分组，中间表 服务实现类
 * </p>
 *
 */
@Service
public class RUserGroupRoleGroupServiceImpl extends MybatisJpaServiceImpl<RUserGroupRoleGroupMapper, RUserGroupRoleGroupRepository, RUserGroupRoleGroup> implements IRUserGroupRoleGroupService {

}
