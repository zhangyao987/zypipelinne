package cn.com.zy.admin.controller;

import cn.com.zy.admin.entity.RDeptRole;
import cn.com.zy.admin.service.IRDeptRoleService;
import cn.com.zy.controller.AbstractMybatisRController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 部门-角色，中间表 前端控制器
 * </p>
 */
@RestController
@RequestMapping(value = "/admin/dept-role", name = "部门-角色中间表控制器")
public class RDeptRoleController extends AbstractMybatisRController<IRDeptRoleService, RDeptRole> {
    public RDeptRoleController() {
        this.mappingFields.add("deptId");
        this.mappingFields.add("roleId");
    }
}
