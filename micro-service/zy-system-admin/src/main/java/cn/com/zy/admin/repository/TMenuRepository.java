package cn.com.zy.admin.repository;

import cn.com.zy.admin.entity.TMenu;
import cn.com.zy.repository.SpringJpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * <p>
 * 前端路由菜单信息表 Repository 接口
 * </p>
 *
 */
public interface TMenuRepository extends SpringJpaRepository<TMenu, String> {
    /**
     * @description: <p>根据角色ID查询该角色直接分配的前端路由菜单权限</p>
     * @param: roleId
     * @return: List<TMenu>
     */
    @Query("from TMenu where id in ( select menuId from RMenuRole where roleId = ?1)")
    List<TMenu> findAllByRoleId(String roleId);
}
