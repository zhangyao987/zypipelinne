package cn.com.zy.admin.service.impl;

import cn.com.zy.admin.entity.TGroup;
import cn.com.zy.admin.mapper.TGroupMapper;
import cn.com.zy.admin.repository.TGroupRepository;
import cn.com.zy.admin.service.ITGroupService;
import cn.com.zy.service.impl.MybatisJpaServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 分组信息表 服务实现类
 * </p>
 *
 */
@Service
public class TGroupServiceImpl extends MybatisJpaServiceImpl<TGroupMapper, TGroupRepository, TGroup> implements ITGroupService {
    @Override
    public List<TGroup> findAllRoleGroupByRoleId(String roleId) {
        return this.baseRepository.findAllRoleGroupByRoleId(roleId);
    }

    @Override
    public List<TGroup> findAllUserGroupByUserId(String userId) {
        return this.baseRepository.findAllUserGroupByUserId(userId);
    }

    @Override
    public List<TGroup> findAllRoleGroupByUserId(String userId) {
        return this.baseRepository.findAllRoleGroupByUserId(userId);
    }

    @Override
    public List<TGroup> findAllRoleGroupByUserGroupId(String userGroupId) {
        return this.baseRepository.findAllRoleGroupByUserGroupId(userGroupId);
    }

    @Override
    public List<TGroup> findAllUserGroupByRoleGroupId(String roleGroupId) {
        return this.baseRepository.findAllUserGroupByRoleGroupId(roleGroupId);
    }
}
