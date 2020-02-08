package cn.com.zy.admin.controller;

import cn.com.zy.admin.entity.RMenuRole;
import cn.com.zy.admin.service.IRMenuRoleService;
import cn.com.zy.controller.AbstractMybatisRController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 菜单-角色中间表 前端控制器
 * </p>
 *
 */
@RestController
@RequestMapping(value = "/admin/menu-role", name = "菜单-角色中间表控制器")
public class RMenuRoleController extends AbstractMybatisRController<IRMenuRoleService, RMenuRole> {
    public RMenuRoleController() {
        this.mappingFields.add("menuId");
        this.mappingFields.add("roleId");
    }
}
