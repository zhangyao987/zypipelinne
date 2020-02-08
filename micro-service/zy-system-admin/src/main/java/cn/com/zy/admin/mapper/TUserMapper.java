package cn.com.zy.admin.mapper;

import cn.com.zy.admin.entity.TUser;
import cn.com.zy.mapper.MybatisBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 */
@Mapper
public interface TUserMapper extends MybatisBaseMapper<TUser> {

}
