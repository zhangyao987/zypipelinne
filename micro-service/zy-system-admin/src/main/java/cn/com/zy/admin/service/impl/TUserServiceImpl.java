package cn.com.zy.admin.service.impl;

import cn.com.zy.admin.entity.TUser;
import cn.com.zy.admin.mapper.TUserMapper;
import cn.com.zy.admin.repository.TUserRepository;
import cn.com.zy.admin.service.ITUserService;
import cn.com.zy.service.impl.MybatisJpaServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 */
@Service
public class TUserServiceImpl extends MybatisJpaServiceImpl<TUserMapper, TUserRepository, TUser> implements ITUserService {
    @Override
    public Boolean updatePasswordById(String id, String password) {
        TUser user = new TUser();
        user.setId(id);
        user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        return SqlHelper.retBool(this.baseMapper.updateById(user));
    }

    @Override
    public TUser login(String loginName, String password) {
        QueryWrapper<TUser> qw = new QueryWrapper<TUser>();
        qw.eq("LOGIN_NAME", loginName).eq("PASSWORD", DigestUtils.md5DigestAsHex(password.getBytes()));
        return this.baseMapper.selectOne(qw);
    }

    @Override
    public List<TUser> findAllByLoginName(String loginName) {
        return this.baseRepository.findAllByLoginNameIgnoreCase(loginName);
    }

    @Override
    public List<TUser> findAllByDeptId(String deptId) {
        return this.baseRepository.findAllByDeptId(deptId);
    }

    @Override
    public List<TUser> findAllByRoleId(String roleId) {
        return this.baseRepository.findAllByRoleId(roleId);
    }

    @Override
    public List<TUser> findAllByUserGroupId(String userGroupId) {
        return this.baseRepository.findAllByUserGroupId(userGroupId);
    }

    @Override
    public List<TUser> findAllByRoleGroupId(String roleGroupId) {
        return this.baseRepository.findAllByRoleGroupId(roleGroupId);
    }
}
