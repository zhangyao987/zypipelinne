package cn.com.zy.admin.mapper;

import cn.com.zy.admin.entity.TDictionary;
import cn.com.zy.mapper.MybatisBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 业务字典（含业务字典分类）表 Mapper 接口
 * </p>
 *
 */
@Mapper
public interface TDictionaryMapper extends MybatisBaseMapper<TDictionary> {

}
