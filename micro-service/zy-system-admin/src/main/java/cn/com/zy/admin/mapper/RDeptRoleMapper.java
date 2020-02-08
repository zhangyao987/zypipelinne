package cn.com.zy.admin.mapper;

import cn.com.zy.admin.entity.RDeptRole;
import cn.com.zy.mapper.MybatisBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 部门-角色，中间表 Mapper 接口
 * </p>
 *
 */
@Mapper
public interface RDeptRoleMapper extends MybatisBaseMapper<RDeptRole> {

}
