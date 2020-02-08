package cn.com.zy.admin.repository;


import cn.com.zy.admin.entity.RUserGroup;
import cn.com.zy.repository.SpringJpaRepository;

/**
 * <p>
 * 用户-分组中间表 Repository 接口
 * </p>
 *
 */
public interface RUserGroupRepository extends SpringJpaRepository<RUserGroup, String> {
}
