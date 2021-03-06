package cn.com.zy.admin.service;


import cn.com.zy.admin.entity.TUser;
import cn.com.zy.service.IMybatisService;

import java.util.List;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 */
public interface ITUserService extends IMybatisService<TUser> {
    /**
     * @description: <p>修改用户密码</p>
     * @param: id
     * @param: password
     * @return: Boolean
     */
    Boolean updatePasswordById(String id, String password);
    /**
     * @description: <p>根据用户名和密码登录</p>
     * @param: loginName
     * @param: password
     * @return: TUser
     */
    TUser login(String loginName, String password);
    /**
     * @description: <p>根据登录名称，模糊查询满足的用户，排出已删除的用户</p>
     * @param loginName 登录显示名称
     * @return List<TUser>
     */
    List<TUser> findAllByLoginName(String loginName);
    /**
     * @description: <p>根据部门id,查询部门下的所有直接授予的可用用户</p>
     * @param: deptId
     * @return: List<TUser>
     */
    List<TUser> findAllByDeptId(String deptId);
    /**
     * @description: <p>根据 角色ID，查询该角色下的直接分配的所有用户信息</p>
     */
    List<TUser> findAllByRoleId(String roleId);
    /**
     * @description: <p>根据 用户组ID，查询该用户组下的直接分配的所有用户信息</p>
     */
    List<TUser> findAllByUserGroupId(String userGroupId);
    /**
     * @description: <p>根据 角色组ID，查询该角色组下的直接分配的所有用户信息</p>
     */
    List<TUser> findAllByRoleGroupId(String roleGroupId);
}
