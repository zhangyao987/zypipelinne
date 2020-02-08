package cn.com.zy.admin.controller;


import cn.com.zy.admin.entity.TMenu;
import cn.com.zy.admin.service.ITMenuService;
import cn.com.zy.admin.service.ITRoleService;
import cn.com.zy.admin.service.ITSecurityService;
import cn.com.zy.controller.AbstractMybatisTController;
import cn.com.zy.util.api.ApiResult;
import cn.com.zy.util.api.ApiResultWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 前端路由菜单信息表 前端控制器
 * </p>
 *
 */
@RestController
@RequestMapping(value = "/admin/menu", name = "前端路由表控制器")
public class TMenuController extends AbstractMybatisTController<ITMenuService, TMenu> {

    @Autowired
    protected ITSecurityService securityService;
    @Autowired
    protected ITRoleService roleService;

    @PostMapping(value = "sync", name = "批量同步前端路由表信息")
    public ApiResult sync(HttpServletRequest request, @RequestBody List<TMenu> menus) {
        return ApiResultWrapper.success(this.baseService.syncMenus(menus));
    }

    @GetMapping(value = "all-securities", name = "根据路由ID，查询该路由直接关联的所有后端服务API列表信息")
    public ApiResult allSecurities(HttpServletRequest request, String id) {
        return ApiResultWrapper.success(this.securityService.findAllByMenuId(id));
    }

    @GetMapping(value = "all-roles", name = "根据路由ID，查询该路由直接关联的角色列表")
    public ApiResult allRoles(HttpServletRequest request, String id) {
        return ApiResultWrapper.success(this.roleService.findAllByMenuId(id));
    }
}
