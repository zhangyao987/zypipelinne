package cn.com.zy.admin.controller;


import cn.com.zy.admin.entity.TSecurity;
import cn.com.zy.admin.service.ITSecurityService;
import cn.com.zy.controller.AbstractMybatisTController;
import cn.com.zy.util.api.ApiResult;
import cn.com.zy.util.api.ApiResultWrapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 安全资源定义信息表 前端控制器
 * </p>
 *
 */
@RestController
@RequestMapping(value = "/admin/security", name = "服务API资源表控制器")
public class TSecurityController extends AbstractMybatisTController<ITSecurityService, TSecurity> {

    @PostMapping(value = "init-micro-service-security", name = "初始化某服务的所有系统内置的API权限资源信息")
    public ApiResult initAuthorities(@RequestBody List<TSecurity> entities){
        this.baseService.initAuthorities(entities);
        return ApiResultWrapper.success();
    }
}

