package cn.com.zy.admin.controller;

import cn.com.zy.admin.entity.TDept;
import cn.com.zy.admin.service.ITDeptService;
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
 * 部门信息表 前端控制器
 * </p>
 *
 */
@RestController
@RequestMapping(value = "/admin/dept", name = "部门表控制器")
public class TDeptController extends AbstractMybatisTController<ITDeptService, TDept> {

    @Autowired
    protected ITUserService userService;
    @Autowired
    protected ITRoleService roleService;

    @GetMapping(value = "query-all-tree", name = "根据ID查询符合条件的tree结构数据")
    public ApiResult queryAllTree(HttpServletRequest request, String id) {
        return ApiResultWrapper.success(this.baseService.queryTree(id));
    }

    @GetMapping(value = "all-users", name = "根据部门ID获取部门下直接关联的所有可用用户")
    public ApiResult allUsers(HttpServletRequest request, String id) {
        return ApiResultWrapper.success(this.userService.findAllByDeptId(id));
    }

    @GetMapping(value = "all-roles", name = "根据部门ID获取部门下直接关联的所有可用角色")
    public ApiResult allRoles(HttpServletRequest request, String id) {
        return ApiResultWrapper.success(this.roleService.findAllByDeptId(id));
    }
}
