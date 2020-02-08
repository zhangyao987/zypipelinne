package cn.com.zy.admin.controller;

import cn.com.zy.admin.entity.TGroup;
import cn.com.zy.admin.service.ITGroupService;
import cn.com.zy.admin.service.ITRoleService;
import cn.com.zy.admin.service.ITUserService;
import cn.com.zy.controller.AbstractMybatisTController;
import cn.com.zy.util.api.ApiResult;
import cn.com.zy.util.api.ApiResultWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 分组信息表 前端控制器
 * </p>
 *
 */
@RestController
@RequestMapping(value = "/admin/group", name = "【用户|角色】分组表控制器")
public class TGroupController extends AbstractMybatisTController<ITGroupService, TGroup> {

    @Autowired
    protected ITUserService userService;
    @Autowired
    protected ITGroupService groupService;
    @Autowired
    protected ITRoleService roleService;

    @GetMapping(value = "all-role-group-users", name = "根据角色组ID，查询该角色组下直接关联的所有可用用户")
    public ApiResult allRoleGroupUsers(HttpServletRequest request, String id) {
        return ApiResultWrapper.success(this.userService.findAllByRoleGroupId(id));
    }

    @GetMapping(value = "all-user-group-users", name = "根据用户组ID，查询该用户组下直接关联的所有可用用户")
    public ApiResult allUserGroupUsers(HttpServletRequest request, String id) {
        return ApiResultWrapper.success(this.userService.findAllByUserGroupId(id));
    }

    @GetMapping(value = "all-role-groups", name = "根据用户组ID，查询该用户组下直接关联的所有可用角色组")
    public ApiResult allRoleGroups(HttpServletRequest request, String id) {
        return ApiResultWrapper.success(this.groupService.findAllRoleGroupByUserGroupId(id));
    }

    @GetMapping(value = "all-user-groups", name = "根据角色组ID，查询该角色组下直接关联的所有可用用户组")
    public ApiResult allUserGroups(HttpServletRequest request, String id) {
        return ApiResultWrapper.success(this.groupService.findAllUserGroupByRoleGroupId(id));
    }

    @GetMapping(value = "all-roles", name = "根据角色组ID，查询该角色组下直接关联的所有可用角色")
    public ApiResult allRoles(HttpServletRequest request, String id) {
        return ApiResultWrapper.success(this.roleService.findAllByRoleGroupId(id));
    }
}
