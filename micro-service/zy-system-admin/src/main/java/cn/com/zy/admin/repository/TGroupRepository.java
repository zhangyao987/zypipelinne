package cn.com.zy.admin.repository;

import cn.com.zy.admin.entity.TGroup;
import cn.com.zy.repository.SpringJpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * <p>
 * 分组信息表 Repository 接口
 * </p>
 *
 */
public interface TGroupRepository extends SpringJpaRepository<TGroup, String> {
    /**
     * @description: <p>根据角色ID，查询该角色下的所有直接分配的分组信息</p>
     *               <p>分组有两个概念，根据category字段识别，是用户分组还是角色分组</p>
     */
    @Query("from TGroup where id in ( select groupId from RRoleGroup where roleId = ?1)")
    List<TGroup> findAllRoleGroupByRoleId(String roleId);
    /**
     * @description: <p>根据用户ID，查询该用户下的所有直接分配的分组信息</p>
     *               <p>分组有两个概念，根据category字段识别，是用户分组还是角色分组</p>
     */
    @Query("from TGroup where id in ( select groupId from RUserGroup where userId = ?1)")
    List<TGroup> findAllUserGroupByUserId(String userId);
    /**
     * @description: <p>根据用户ID，查询该用户下的所有直接分配的分组信息</p>
     *               <p>分组有两个概念，根据category字段识别，是用户分组还是角色分组</p>
     */
    @Query("from TGroup where id in ( select roleGroupId from RUserRoleGroup where userId = ?1)")
    List<TGroup> findAllRoleGroupByUserId(String userId);
    /**
     * @description: <p>根据用户分组ID，查询该用户分组所有直接分配的角色分组信息</p>
     *               <p>分组有两个概念，根据category字段识别，是用户分组还是角色分组</p>
     * @param: userGroupId
     * @return: List<TGroup>
     */
    @Query("from TGroup where id in ( select roleGroupId from RUserGroupRoleGroup where userGroupId = ?1)")
    List<TGroup> findAllRoleGroupByUserGroupId(String userGroupId);
    /**
     * @description: <p>根据角色分组ID，查询该角色分组所有直接分配的用户分组信息</p>
     *               <p>分组有两个概念，根据category字段识别，是用户分组还是角色分组</p>
     * @param: roleGroupId
     * @return: List<TGroup>
     */
    @Query("from TGroup where id in ( select userGroupId from RUserGroupRoleGroup where roleGroupId = ?1)")
    List<TGroup> findAllUserGroupByRoleGroupId(String roleGroupId);
}
