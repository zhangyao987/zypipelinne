package cn.com.zy.admin.service.impl;

import cn.com.zy.admin.entity.RMenuRole;
import cn.com.zy.admin.mapper.RMenuRoleMapper;
import cn.com.zy.admin.repository.RMenuRoleRepository;
import cn.com.zy.admin.service.IRMenuRoleService;
import cn.com.zy.service.impl.MybatisJpaServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 菜单-角色中间表 服务实现类
 * </p>
 *
 */
@Service
public class RMenuRoleServiceImpl extends MybatisJpaServiceImpl<RMenuRoleMapper, RMenuRoleRepository, RMenuRole> implements IRMenuRoleService {

}
