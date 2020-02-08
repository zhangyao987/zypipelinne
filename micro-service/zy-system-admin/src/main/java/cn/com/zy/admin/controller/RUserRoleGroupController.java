package cn.com.zy.admin.controller;

import cn.com.zy.admin.entity.RUserRoleGroup;
import cn.com.zy.admin.service.IRUserRoleGroupService;
import cn.com.zy.controller.AbstractMybatisRController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户-角色分组，中间表 前端控制器
 * </p>
 *
 */
@RestController
@RequestMapping(value = "/admin/user-role-group", name = "部门-角色组中间表控制器")
public class RUserRoleGroupController extends AbstractMybatisRController<IRUserRoleGroupService, RUserRoleGroup> {
    public RUserRoleGroupController() {
        this.mappingFields.add("userId");
        this.mappingFields.add("roleGroupId");
    }
}
