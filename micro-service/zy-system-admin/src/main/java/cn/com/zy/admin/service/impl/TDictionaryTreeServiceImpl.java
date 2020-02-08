package cn.com.zy.admin.service.impl;

import cn.com.zy.admin.entity.extend.TDictionaryTree;
import cn.com.zy.admin.repository.TDictionaryTreeRepository;
import cn.com.zy.admin.service.ITDictionaryTreeService;
import cn.com.zy.service.impl.SpringJpaServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 业务字典（含业务字典分类）表 服务实现类
 * </p>
 *
 */
@Service
@Slf4j
public class TDictionaryTreeServiceImpl extends SpringJpaServiceImpl<TDictionaryTreeRepository, TDictionaryTree> implements ITDictionaryTreeService {

}
