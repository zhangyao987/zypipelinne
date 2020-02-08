package cn.com.zy.admin.service.impl;

import cn.com.zy.admin.entity.RUserGroup;
import cn.com.zy.admin.mapper.RUserGroupMapper;
import cn.com.zy.admin.repository.RUserGroupRepository;
import cn.com.zy.admin.service.IRUserGroupService;
import cn.com.zy.service.impl.MybatisJpaServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户-分组中间表 服务实现类
 * </p>
 *
 */
@Service
public class RUserGroupServiceImpl extends MybatisJpaServiceImpl<RUserGroupMapper, RUserGroupRepository, RUserGroup> implements IRUserGroupService {

}
