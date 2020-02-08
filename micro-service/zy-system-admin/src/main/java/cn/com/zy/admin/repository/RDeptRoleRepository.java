package cn.com.zy.admin.repository;


import cn.com.zy.admin.entity.RDeptRole;
import cn.com.zy.repository.SpringJpaRepository;

/**
 * <p>
 * 部门-角色，中间表 Repository 接口
 * </p>
 *
 */
public interface RDeptRoleRepository extends SpringJpaRepository<RDeptRole, String> {
}
