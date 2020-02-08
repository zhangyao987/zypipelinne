package cn.com.zy.controller;

import cn.com.zy.entity.MybatisEntity;
import cn.com.zy.service.IMybatisService;
import cn.com.zy.util.MybatisPage;
import cn.com.zy.util.api.ApiResult;
import cn.com.zy.util.api.ApiResultWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

public class AbstractMybatisTController<S extends IMybatisService<T>, T extends MybatisEntity> {

    @Autowired
    protected S baseService;

    /**
     * @description: <p>单个新增</p>
     * @param: request
     * @param: entity
     * @return: ApiResult，数据结构满足开发规范
     */
    @PostMapping(value = "add", name="单个新增")
    public ApiResult add(HttpServletRequest request, T entity){
        baseService.save(entity);
        return ApiResultWrapper.success(entity);
    }

    /**
     * @description: <p>根据id删除</p>
     * @param: request
     * @param: id
     * @return: ApiResult，数据结构满足开发规范
     */
    @PostMapping(value = "del", name="根据ID删除")
    public ApiResult del(HttpServletRequest request, String id){
        baseService.removeById(id);
        return ApiResultWrapper.success();
    }
    /**
     * @description: <p>单个修改</p>
     * @param: request
     * @param: <p>entity实体,若仅修改某几个字段，则可以将其他字段设置为null即可</p>
     *         <p>null与空字符串的说明</p>
     *         <p>null --> 无变化</p>
     *         <p>"" --> ""</p>
     * @return: ApiResult，数据结构满足开发规范
     */
    @PostMapping(value = "edit", name="根据ID编辑")
    public ApiResult edit(HttpServletRequest request, T entity){
        entity.setCreatedBy(null);
        entity.setCreatedDate(null);
        baseService.updateById(entity);
        return ApiResultWrapper.success(entity);
    }

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
    public ApiResult queryAll(HttpServletRequest request, @RequestBody(required = false) MybatisPage<T> page){
        return ApiResultWrapper.success(baseService.list(page));
    }
    /**
     * @description: <p>根据条件分页查询数据</p>
     * @param: request
     * @param: page
     * @return: ApiResult，数据结构满足开发规范
     */
    @PostMapping(value = "query-page", name="根据动态SQL条件查询独立实体[分页]")
    public ApiResult queryPage(HttpServletRequest request, @RequestBody MybatisPage<T> page){
        return ApiResultWrapper.success(baseService.page(page));
    }

}
