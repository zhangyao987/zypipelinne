package cn.com.zy.admin.service;


import cn.com.zy.admin.entity.TSecurity;
import cn.com.zy.service.IMybatisService;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 安全资源定义信息表 服务类
 * </p>
 *
 */
public interface ITSecurityService extends IMybatisService<TSecurity> {
    /**
     * @description: <p>初始化服务controller信息,仅仅涉及系统内置的，不包含用户自定义的</p>
     */
    void initAuthorities(List<TSecurity> entities);
    /**
     * @description: <p>根据路由菜单ID，查看该路由菜单直接分配的所有后端资源权限列表</p>
     */
    List<TSecurity> findAllByMenuId(String menuId);
    /**
     * @description: <p>返回系统中包含的所有资源与角色的映射关系</p>
     */
    Map<String, Set<String>> findAllSecurityRoleMapping();
}
