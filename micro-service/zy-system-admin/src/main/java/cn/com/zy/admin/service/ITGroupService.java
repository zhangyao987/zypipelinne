package cn.com.zy.admin.service;


import cn.com.zy.admin.entity.TGroup;
import cn.com.zy.service.IMybatisService;

import java.util.List;

/**
 * <p>
 * 分组信息表 服务类
 * </p>
 *
 */
public interface ITGroupService extends IMybatisService<TGroup> {
    /**
     * @description: <p>根据角色ID，查询该角色下的所有直接分配的分组信息</p>
     *               <p>分组有两个概念，根据category字段识别，是用户分组还是角色分组</p>
     */
    List<TGroup> findAllRoleGroupByRoleId(String roleId);
    /**
     * @description: <p>根据用户ID，查询该用户下的所有直接分配的分组信息</p>
     *               <p>分组有两个概念，根据category字段识别，是用户分组还是角色分组</p>
     */
    List<TGroup> findAllUserGroupByUserId(String userId);
    /**
     * @description: <p>根据用户ID，查询该用户下的所有直接分配的分组信息</p>
     *               <p>分组有两个概念，根据category字段识别，是用户分组还是角色分组</p>
     */
    List<TGroup> findAllRoleGroupByUserId(String userId);
    /**
     * @description: <p>根据用户分组ID，查询该用户分组所有直接分配的角色分组信息</p>
     *               <p>分组有两个概念，根据category字段识别，是用户分组还是角色分组</p>
     * @param: userGroupId
     * @return: List<TGroup>
     */
    List<TGroup> findAllRoleGroupByUserGroupId(String userGroupId);
    /**
     * @description: <p>根据角色分组ID，查询该角色分组所有直接分配的用户分组信息</p>
     *               <p>分组有两个概念，根据category字段识别，是用户分组还是角色分组</p>
     * @param: roleGroupId
     * @return: List<TGroup>
     */
    List<TGroup> findAllUserGroupByRoleGroupId(String roleGroupId);
}
