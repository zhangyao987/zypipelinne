package cn.com.zy.admin.repository;


import cn.com.zy.admin.entity.RUserDept;
import cn.com.zy.repository.SpringJpaRepository;

/**
 * <p>
 * 用户-部门中间表 Repository 接口
 * </p>
 *
 */
public interface RUserDeptRepository extends SpringJpaRepository<RUserDept, String> {
}
