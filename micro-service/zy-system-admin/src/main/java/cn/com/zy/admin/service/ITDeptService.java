package cn.com.zy.admin.service;


import cn.com.zy.admin.entity.TDept;
import cn.com.zy.service.IMybatisService;

import java.util.List;

/**
 * <p>
 * 部门信息表 服务类
 * </p>
 *
 */
public interface ITDeptService extends IMybatisService<TDept> {
    /**
     * @description: <p>根据ID查询tree结构数据</p>
     *               <p>不传递id时,或只传递了一个null或''，则查询的是所有的部门tree结构</p>
     * @param: id, 支持多个
     * @return: List<TDept>
     */
    List<TDept> queryTree(String... ids);

    /**
     * @description: <p>根据角色ID，查询该角色下的所有直接分配的部门信息</p>
     */
    List<TDept> findAllByRoleId(String roleId);
    /**
     * @description: <p>根据用户ID，查询该用户下的所有直接分配的部门信息</p>
     */
    List<TDept> findAllByUserId(String userId);

}
