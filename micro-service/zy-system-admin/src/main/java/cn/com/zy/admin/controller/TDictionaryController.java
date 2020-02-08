package cn.com.zy.admin.controller;


import cn.com.zy.admin.entity.TDictionary;
import cn.com.zy.admin.entity.extend.TDictionaryTree;
import cn.com.zy.admin.service.ITDictionaryService;
import cn.com.zy.admin.service.ITDictionaryTreeService;
import cn.com.zy.controller.AbstractMybatisTController;
import cn.com.zy.util.SpringJpaPage;
import cn.com.zy.util.api.ApiResult;
import cn.com.zy.util.api.ApiResultWrapper;
import cn.com.zy.util.enums.DictionaryCategory;
import cn.com.zy.util.enums.Gender;
import cn.com.zy.util.enums.MicroService;
import cn.com.zy.util.enums.Boolean;
import cn.com.zy.util.enums.SystemDictionaryCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 业务字典（含业务字典分类）表 前端控制器
 * </p>
 *
 */
@RestController
@RequestMapping(value = "/admin/dictionary", name = "字典（或字典分类）表控制器")
public class TDictionaryController extends AbstractMybatisTController<ITDictionaryService, TDictionary> {

    @Autowired
    private ITDictionaryTreeService dictionaryTreeService;


    @GetMapping(value = "query-base-system-dictionaries", name="查询基础系统内置数据字典(源自枚举)")
    public ApiResult queryBaseDictionaries(HttpServletRequest request){
        Map<String, List<Map<String, String>>> dictionaries = new HashMap<String, List<Map<String, String>>>();
        dictionaries.put(SystemDictionaryCategory.REFRESH_TRUE_OR_FALSE.name(), Boolean.getAllDictionaries());
        dictionaries.put(SystemDictionaryCategory.REFRESH_GENDER.name(), Gender.getAllDictionaries());
        dictionaries.put(SystemDictionaryCategory.REFRESH_DICTIONARY_CATEGORY.name(), DictionaryCategory.getAllDictionaries());
        dictionaries.put(SystemDictionaryCategory.REFRESH_MICRO_SERVICE.name(), MicroService.getAllDictionaries());
        return ApiResultWrapper.success(dictionaries);
    }

    @PostMapping(value = "sync-dictionaries", name="批量同步字典值")
    public ApiResult syncDictionaries(@RequestBody(required = false) List<TDictionary> dictionaries) {
        this.baseService.syncDictionaries(dictionaries);
        return ApiResultWrapper.success();
    }

    @PostMapping(value = "query-all-tree", name="根据动态SQL条件查询独立实体[非分页]")
    public ApiResult queryAll(HttpServletRequest request, @RequestBody(required = false) SpringJpaPage<TDictionaryTree> page){
        return ApiResultWrapper.success(this.dictionaryTreeService.list(page));
    }

}
