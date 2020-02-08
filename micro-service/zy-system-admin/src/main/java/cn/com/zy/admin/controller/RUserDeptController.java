package cn.com.zy.admin.controller;

import cn.com.zy.admin.entity.RUserDept;
import cn.com.zy.admin.service.IRUserDeptService;
import cn.com.zy.controller.AbstractMybatisRController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户-部门中间表 前端控制器
 * </p>
 *
 */
@RestController
@RequestMapping(value = "/admin/user-dept", name = "用户-部门中间表控制器")
public class RUserDeptController extends AbstractMybatisRController<IRUserDeptService, RUserDept> {
    public RUserDeptController() {
        this.mappingFields.add("userId");
        this.mappingFields.add("deptId");
    }
}
