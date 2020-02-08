package cn.com.zy.admin.controller;

import cn.com.zy.admin.entity.RRoleGroup;
import cn.com.zy.admin.service.IRRoleGroupService;
import cn.com.zy.controller.AbstractMybatisRController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 角色-分组中间表 前端控制器
 * </p>
 *
 */
@RestController
@RequestMapping(value = "/admin/role-group", name = "角色与角色组中间表控制器")
public class RRoleGroupController extends AbstractMybatisRController<IRRoleGroupService, RRoleGroup> {
    public RRoleGroupController() {
        this.mappingFields.add("groupId");
        this.mappingFields.add("roleId");
    }
}
