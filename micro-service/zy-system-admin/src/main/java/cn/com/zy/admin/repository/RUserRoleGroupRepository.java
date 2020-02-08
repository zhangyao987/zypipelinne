package cn.com.zy.admin.repository;


import cn.com.zy.admin.entity.RUserRoleGroup;
import cn.com.zy.repository.SpringJpaRepository;

/**
 * <p>
 * 用户-角色分组，中间表 Repository 接口
 * </p>
 *
 */
public interface RUserRoleGroupRepository extends SpringJpaRepository<RUserRoleGroup, String> {
}
