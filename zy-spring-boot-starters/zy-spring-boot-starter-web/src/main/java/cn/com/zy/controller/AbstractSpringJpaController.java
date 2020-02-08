package cn.com.zy.controller;

import cn.com.zy.service.ISpringJpaService;
import cn.com.zy.util.SpringJpaPage;
import cn.com.zy.util.api.ApiResult;
import cn.com.zy.util.api.ApiResultWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

public abstract class AbstractSpringJpaController<S extends ISpringJpaService<T>, T> {

    @Autowired
    protected S baseService;

    /**
     * @description: <p>根据id查询</p>
     * @param: request
     * @param: id
     * @return: ApiResult，数据结构满足开发规范
     */
    @GetMapping(value = "query-by-id", name="根据ID查询独立实体")
    public ApiResult queryById(HttpServletRequest request, String id){
        return ApiResultWrapper.success(baseService.getById(id));
    }

    /**
     * @description: <p>根据条件查询全部数据</p>
     * @param: request
     * @param: queryConditions
     * @return: ApiResult，数据结构满足开发规范
     */
    @PostMapping(value = "query-all", name="根据动态SQL条件查询独立实体[非分页]")
    public ApiResult queryAll(HttpServletRequest request, @RequestBody(required = false) SpringJpaPage<T> page){
        return ApiResultWrapper.success(baseService.list(page));
    }

    /**
     * @description: <p>根据条件分页查询数据</p>
     * @param: request
     * @param: page
     * @return: ApiResult，数据结构满足开发规范
     */
    @PostMapping(value = "query-page", name="根据动态SQL条件查询独立实体[分页]")
    public ApiResult queryPage(HttpServletRequest request, @RequestBody SpringJpaPage<T> page){
        return ApiResultWrapper.success(baseService.page(page));
    }

}
