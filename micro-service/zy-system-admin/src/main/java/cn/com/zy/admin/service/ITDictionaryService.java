package cn.com.zy.admin.service;


import cn.com.zy.admin.entity.TDictionary;
import cn.com.zy.service.IMybatisService;

import java.util.List;

/**
 * <p>
 * 业务字典（含业务字典分类）表 服务类
 * </p>
 *
 */
public interface ITDictionaryService extends IMybatisService<TDictionary> {
    /**
     * @description: <p>批量同步字典值, 新增或修改</p>
     * @param: dictionaries
     */
    public void syncDictionaries(List<TDictionary> dictionaries);

}
