package cn.com.zy.admin.service.impl;

import cn.com.zy.admin.entity.RUserRoleGroup;
import cn.com.zy.admin.mapper.RUserRoleGroupMapper;
import cn.com.zy.admin.repository.RUserRoleGroupRepository;
import cn.com.zy.admin.service.IRUserRoleGroupService;
import cn.com.zy.service.impl.MybatisJpaServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户-角色分组，中间表 服务实现类
 * </p>
 *
 */
@Service
public class RUserRoleGroupServiceImpl extends MybatisJpaServiceImpl<RUserRoleGroupMapper, RUserRoleGroupRepository, RUserRoleGroup> implements IRUserRoleGroupService {

}
