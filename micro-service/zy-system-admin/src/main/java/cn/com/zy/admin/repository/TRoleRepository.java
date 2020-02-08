package cn.com.zy.admin.repository;

import cn.com.zy.admin.entity.TRole;
import cn.com.zy.repository.SpringJpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * <p>
 * 角色信息表 Repository 接口
 * </p>
 *
 */
public interface TRoleRepository extends SpringJpaRepository<TRole, String> {

    /**
     * @description: <p>查询当前用户直接以及简洁具有的所有角色ID，用户的角色来源可分为：</p>
     *               --><p>用户可直接授予角色, select roleId from RUserRole where userId = ?1</p>
     *                  <p>用户可直接授予角色组，角色组可直接授予角色，select roleId from RRoleGroup where groupId in (select roleGroupId from RUserRoleGroup where userId = ?1)</p>
     *                  <p>用户可直接授予部门，部门可直接授予角色，select roleId from RDeptRole where deptId in (select deptId from RUserDept where userId = ?1)</p>
     *                  <p>用户可直接授予用户组，用户组可直接授予角色组，角色组可直接授予角色, select roleId from RRoleGroup where groupId in (select roleGroupId from RUserGroupRoleGroup where userGroupId in (select groupId from RUserGroup where userId = ?1))</p>
     */
    @Query("select id from TRole where id in (select roleId from RUserRole where userId = ?1)")
    public List<String> findAllFromUserRole(String userId);
    /**
     * @description: <p>查询当前用户直接以及简洁具有的所有角色ID，用户的角色来源可分为：</p>
     *                  <p>用户可直接授予角色, select roleId from RUserRole where userId = ?1</p>
     *               --><p>用户可直接授予角色组，角色组可直接授予角色，select roleId from RRoleGroup where groupId in (select roleGroupId from RUserRoleGroup where userId = ?1)</p>
     *                  <p>用户可直接授予部门，部门可直接授予角色，select roleId from RDeptRole where deptId in (select deptId from RUserDept where userId = ?1)</p>
     *                  <p>用户可直接授予用户组，用户组可直接授予角色组，角色组可直接授予角色, select roleId from RRoleGroup where groupId in (select roleGroupId from RUserGroupRoleGroup where userGroupId in (select groupId from RUserGroup where userId = ?1))</p>
     */
    @Query("select id from TRole where id in (select roleId from RRoleGroup where groupId in (select roleGroupId from RUserRoleGroup where userId = ?1))")
    public List<String> findAllFromUserRoleGroup(String userId);
    /**
     * @description: <p>查询当前用户直接以及简洁具有的所有角色ID，用户的角色来源可分为：</p>
     *                  <p>用户可直接授予角色, select roleId from RUserRole where userId = ?1</p>
     *                  <p>用户可直接授予角色组，角色组可直接授予角色，select roleId from RRoleGroup where groupId in (select roleGroupId from RUserRoleGroup where userId = ?1)</p>
     *               --><p>用户可直接授予部门，部门可直接授予角色，select roleId from RDeptRole where deptId in (select deptId from RUserDept where userId = ?1)</p>
     *                  <p>用户可直接授予用户组，用户组可直接授予角色组，角色组可直接授予角色, select roleId from RRoleGroup where groupId in (select roleGroupId from RUserGroupRoleGroup where userGroupId in (select groupId from RUserGroup where userId = ?1))</p>
     */
    @Query("select id from TRole where id in (select roleId from RDeptRole where deptId in (select deptId from RUserDept where userId = ?1))")
    public List<String> findAllFromUserDept(String userId);
    /**
     * @description: <p>查询当前用户直接以及简洁具有的所有角色ID，用户的角色来源可分为：</p>
     *                  <p>用户可直接授予角色, select roleId from RUserRole where userId = ?1</p>
     *                  <p>用户可直接授予角色组，角色组可直接授予角色，select roleId from RRoleGroup where groupId in (select roleGroupId from RUserRoleGroup where userId = ?1)</p>
     *                  <p>用户可直接授予部门，部门可直接授予角色，select roleId from RDeptRole where deptId in (select deptId from RUserDept where userId = ?1)</p>
     *               --><p>用户可直接授予用户组，用户组可直接授予角色组，角色组可直接授予角色, select roleId from RRoleGroup where groupId in (select roleGroupId from RUserGroupRoleGroup where userGroupId in (select groupId from RUserGroup where userId = ?1))</p>
     */
    @Query("select id from TRole where id in (select roleId from RRoleGroup where groupId in (select roleGroupId from RUserGroupRoleGroup where userGroupId in (select groupId from RUserGroup where userId = ?1)))")
    public List<String> findAllFromUserGroup(String userId);
    /**
     * @description: <p>根据部门ID，查看该部门直接分配的所有角色列表</p>
     */
    @Query("from TRole where id in ( select roleId from RDeptRole where deptId = ?1)")
    List<TRole> findAllByDeptId(String deptId);
    /**
     * @description: <p>根据用户ID，查看该用户直接分配的所有角色列表</p>
     */
    @Query("from TRole where id in ( select roleId from RUserRole where userId = ?1)")
    List<TRole> findAllByUserId(String userId);
    /**
     * @description: <p>根据路由菜单ID，查看该路由菜单直接分配的所有角色列表</p>
     */
    @Query("from TRole where id in ( select roleId from RMenuRole where menuId = ?1)")
    List<TRole> findAllByMenuId(String menuId);
    /**
     * @description: <p>根据路由角色组ID，查看该角色组直接分配的所有角色列表</p>
     */
    @Query("from TRole where id in ( select roleId from RRoleGroup where groupId = ?1)")
    List<TRole> findAllByRoleGroupId(String roleGroupId);
}
