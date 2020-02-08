package cn.com.zy.admin.controller;

import cn.com.zy.admin.entity.RUserGroupRoleGroup;
import cn.com.zy.admin.service.IRUserGroupRoleGroupService;
import cn.com.zy.controller.AbstractMybatisRController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户分组-角色分组，中间表 前端控制器
 * </p>
 *
 */
@RestController
@RequestMapping(value = "/admin/user-group-role-group", name = "用户组-角色组中间表控制器")
public class RUserGroupRoleGroupController extends AbstractMybatisRController<IRUserGroupRoleGroupService, RUserGroupRoleGroup> {
    public RUserGroupRoleGroupController() {
        this.mappingFields.add("userGroupId");
        this.mappingFields.add("roleGroupId");
    }
}
