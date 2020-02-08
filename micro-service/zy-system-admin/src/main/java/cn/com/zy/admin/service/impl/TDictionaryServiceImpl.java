package cn.com.zy.admin.service.impl;

import cn.com.zy.admin.entity.TDictionary;
import cn.com.zy.admin.mapper.TDictionaryMapper;
import cn.com.zy.admin.repository.TDictionaryRepository;
import cn.com.zy.admin.service.ITDictionaryService;
import cn.com.zy.service.impl.MybatisJpaServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 业务字典（含业务字典分类）表 服务实现类
 * </p>
 *
 */
@Service
@Slf4j
public class TDictionaryServiceImpl extends MybatisJpaServiceImpl<TDictionaryMapper, TDictionaryRepository, TDictionary> implements ITDictionaryService {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void syncDictionaries(List<TDictionary> dictionaries) {
        List<TDictionary> history = this.baseMapper.selectBatchIds(dictionaries.stream().map(d -> d.getId()).collect(Collectors.toList()));
        Set<String> ids = history.stream().map(d -> d.getId()).collect(Collectors.toSet());
        dictionaries.forEach(d -> {
            if(ids.contains(d.getId())) {
                this.baseMapper.updateById(d);
            } else {
                this.baseMapper.insert(d);
            }
        });
    }
}
