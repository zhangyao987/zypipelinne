package cn.com.zy.admin.mapper;


import cn.com.zy.admin.dto.SecurityRoleDTO;
import cn.com.zy.admin.entity.TSecurity;
import cn.com.zy.mapper.MybatisBaseMapper;

import java.util.List;

/**
 * <p>
 * 安全资源定义信息表 Mapper 接口
 * </p>
 *
 */
public interface TSecurityMapper extends MybatisBaseMapper<TSecurity> {
    List<SecurityRoleDTO> selectAllSecurityRoleDTO();
}
