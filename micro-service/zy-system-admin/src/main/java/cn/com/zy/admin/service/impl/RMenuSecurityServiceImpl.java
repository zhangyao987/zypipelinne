package cn.com.zy.admin.service.impl;

import cn.com.zy.admin.entity.RMenuSecurity;
import cn.com.zy.admin.mapper.RMenuSecurityMapper;
import cn.com.zy.admin.repository.RMenuSecurityRepository;
import cn.com.zy.admin.service.IRMenuSecurityService;
import cn.com.zy.service.impl.MybatisJpaServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 菜单-安全资源中间表 服务实现类
 * </p>
 *
 */
@Service
public class RMenuSecurityServiceImpl extends MybatisJpaServiceImpl<RMenuSecurityMapper, RMenuSecurityRepository, RMenuSecurity> implements IRMenuSecurityService {

}
