package cn.com.zy.admin.repository;


import cn.com.zy.admin.entity.TDictionary;
import cn.com.zy.repository.SpringJpaRepository;

/**
 * <p>
 * 业务字典（含业务字典分类）表 Repository 接口
 * </p>
 *
 */
public interface TDictionaryRepository extends SpringJpaRepository<TDictionary, String> {
}
