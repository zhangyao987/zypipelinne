package cn.com.zy.admin.service.impl;

import cn.com.zy.admin.entity.TRole;
import cn.com.zy.admin.mapper.TRoleMapper;
import cn.com.zy.admin.repository.TRoleRepository;
import cn.com.zy.admin.service.ITRoleService;
import cn.com.zy.service.impl.MybatisJpaServiceImpl;
import cn.com.zy.util.context.UserContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

/**
 * <p>
 * 角色信息表 服务实现类
 * </p>
 *
 */
@Service
public class TRoleServiceImpl extends MybatisJpaServiceImpl<TRoleMapper, TRoleRepository, TRole> implements ITRoleService {
    @Override
    public HashSet<String> findAllByCurrentUserId() {
        String id = UserContextHolder.getContext();
        HashSet<String> set = new HashSet<String>();
        List<String> list = this.baseRepository.findAllFromUserDept(id);
        set.addAll(list);
        list = this.baseRepository.findAllFromUserGroup(id);
        set.addAll(list);
        list = this.baseRepository.findAllFromUserRole(id);
        set.addAll(list);
        list = this.baseRepository.findAllFromUserRoleGroup(id);
        set.addAll(list);
        return set;
    }

    @Override
    public List<TRole> findAllByDeptId(String deptId) {
        return this.baseRepository.findAllByDeptId(deptId);
    }
    @Override
    public List<TRole> findAllByUserId(String userId) {
        return this.baseRepository.findAllByUserId(userId);
    }

    @Override
    public List<TRole> findAllByMenuId(String menuId) {
        return this.baseRepository.findAllByMenuId(menuId);
    }

    @Override
    public List<TRole> findAllByRoleGroupId(String roleGroupId) {
        return this.baseRepository.findAllByRoleGroupId(roleGroupId);
    }
}
