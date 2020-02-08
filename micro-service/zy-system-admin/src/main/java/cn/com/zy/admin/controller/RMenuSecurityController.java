package cn.com.zy.admin.controller;

import cn.com.zy.admin.entity.RMenuSecurity;
import cn.com.zy.admin.service.IRMenuSecurityService;
import cn.com.zy.controller.AbstractMybatisRController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 菜单-安全资源中间表 前端控制器
 * </p>
 *
 */
@RestController
@RequestMapping(value = "/admin/menu-security", name = "菜单-服务API级别安全资源中间表控制器")
public class RMenuSecurityController extends AbstractMybatisRController<IRMenuSecurityService, RMenuSecurity> {
    public RMenuSecurityController() {
        this.mappingFields.add("menuId");
        this.mappingFields.add("securityId");
    }
}
