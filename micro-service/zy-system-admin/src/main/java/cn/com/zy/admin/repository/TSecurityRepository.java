package cn.com.zy.admin.repository;

import cn.com.zy.admin.entity.TSecurity;
import cn.com.zy.repository.SpringJpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * <p>
 * 安全资源定义信息表 Repository 接口
 * </p>
 *
 */
public interface TSecurityRepository extends SpringJpaRepository<TSecurity, String> {
    /**
     * @description: <p>根据路由菜单ID，查看该路由菜单直接分配的所有后端资源权限列表</p>
     */
    @Query("from TSecurity where id in ( select securityId from RMenuSecurity where menuId = ?1)")
    List<TSecurity> findAllByMenuId(String menuId);
}
