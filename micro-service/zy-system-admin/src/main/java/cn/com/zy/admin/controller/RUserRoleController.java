package cn.com.zy.admin.controller;

import cn.com.zy.admin.entity.RUserRole;
import cn.com.zy.admin.service.IRUserRoleService;
import cn.com.zy.controller.AbstractMybatisRController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户-角色中间表 前端控制器
 * </p>
 *
 */
@RestController
@RequestMapping(value = "/admin/user-role", name = "用户-角色中间表控制器")
public class RUserRoleController extends AbstractMybatisRController<IRUserRoleService, RUserRole> {
    public RUserRoleController() {
        this.mappingFields.add("userId");
        this.mappingFields.add("roleId");
    }
}
