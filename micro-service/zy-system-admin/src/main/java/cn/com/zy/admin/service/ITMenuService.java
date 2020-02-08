package cn.com.zy.admin.service;


import cn.com.zy.admin.entity.TMenu;
import cn.com.zy.service.IMybatisService;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 前端路由菜单信息表 服务类
 * </p>
 *
 */
public interface ITMenuService extends IMybatisService<TMenu> {
    /**
     * @description: <p>根据角色ID查询该角色直接分配的前端路由菜单权限</p>
     * @param: roleId
     * @return: List<TMenu>
     */
    List<TMenu> findAllByRoleId(String roleId);
    /**
     * @description: <p>同步前端路由菜单，清除历史数据，保留本次同步的数据</p>
     * @param:
     * @return:
     */
    Map<String, List<Map<String, String>>> syncMenus(List<TMenu> menus);
    /**
     * @description: <p>查询系统中所有的路由与角色映射关系</p>
     *               <p>map key为路由ID，value为映射的角色ID set集合</p>
     */
    Map<String, Set<String>> findAllRouterRoles();
}
