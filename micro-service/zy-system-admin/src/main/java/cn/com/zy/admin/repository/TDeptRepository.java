package cn.com.zy.admin.repository;

import cn.com.zy.admin.entity.TDept;
import cn.com.zy.repository.SpringJpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * <p>
 * 部门信息表 Repository 接口
 * </p>
 *
 */
public interface TDeptRepository extends SpringJpaRepository<TDept, String> {
    /**
     * @description: <p>根据ID查询tree结构数据</p>
     * @param: id, 支持多个
     *  @return: List<TDept>
     */
    List<TDept> findTDeptsByIdIn(String[] ids);
    /**
     * @description: <p>根据指定的parentId列表，查询符合条件的id数据数组</p>
     * @param: parentIds
     * @return: String[]
     */
    @Query(value = "SELECT id FROM TDept WHERE parentId in (:parentIds)")
    String[] findAllIdsByParentId(@Param(value = "parentIds") String[] parentIds);
    /**
     * @description: <p>根据角色ID，查询该角色下的所有直接分配的部门信息</p>
     */
    @Query("from TDept where id in ( select deptId from RDeptRole where roleId = ?1)")
    List<TDept> findAllByRoleId(String roleId);
    /**
     * @description: <p>根据用户ID，查询该用户下的所有直接分配的部门信息</p>
     */
    @Query("from TDept where id in ( select deptId from RUserDept where userId = ?1)")
    List<TDept> findAllByUserId(String userId);
}
