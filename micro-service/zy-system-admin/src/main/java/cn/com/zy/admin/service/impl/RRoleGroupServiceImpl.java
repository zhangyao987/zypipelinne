package cn.com.zy.admin.service.impl;

import cn.com.zy.admin.entity.RRoleGroup;
import cn.com.zy.admin.mapper.RRoleGroupMapper;
import cn.com.zy.admin.repository.RRoleGroupRepository;
import cn.com.zy.admin.service.IRRoleGroupService;
import cn.com.zy.service.impl.MybatisJpaServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色-分组中间表 服务实现类
 * </p>
 *
 */
@Service
public class RRoleGroupServiceImpl extends MybatisJpaServiceImpl<RRoleGroupMapper, RRoleGroupRepository, RRoleGroup> implements IRRoleGroupService {

}
