package cn.com.zy.admin.controller;

import cn.com.zy.admin.entity.RUserGroup;
import cn.com.zy.admin.service.IRUserGroupService;
import cn.com.zy.controller.AbstractMybatisRController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户-分组中间表 前端控制器
 * </p>
 *
 */
@RestController
@RequestMapping(value = "/admin/user-group", name = "用户-用户组中间表控制器")
public class RUserGroupController extends AbstractMybatisRController<IRUserGroupService, RUserGroup> {
    public RUserGroupController() {
        this.mappingFields.add("userId");
        this.mappingFields.add("groupId");
    }
}
