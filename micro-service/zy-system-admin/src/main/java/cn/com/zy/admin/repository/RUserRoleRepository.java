package cn.com.zy.admin.repository;


import cn.com.zy.admin.entity.RUserRole;
import cn.com.zy.repository.SpringJpaRepository;

/**
 * <p>
 * 用户-角色中间表 Repository 接口
 * </p>
 *
 */
public interface RUserRoleRepository extends SpringJpaRepository<RUserRole, String> {
}
