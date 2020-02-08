package cn.com.zy.admin.service.impl;

import cn.com.zy.admin.entity.TDept;
import cn.com.zy.admin.mapper.TDeptMapper;
import cn.com.zy.admin.repository.TDeptRepository;
import cn.com.zy.admin.service.ITDeptService;
import cn.com.zy.service.impl.MybatisJpaServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 部门信息表 服务实现类
 * </p>
 *
 */
@Service
public class TDeptServiceImpl extends MybatisJpaServiceImpl<TDeptMapper, TDeptRepository, TDept> implements ITDeptService {

    @Override
    public List<TDept> queryTree(String...ids) {
        if (ids.length == 0 || (ids.length == 1 && StringUtils.isEmpty(ids[0]))) {
            String[] parentIds = {null, ""};
            ids = this.baseRepository.findAllIdsByParentId(parentIds);
        }
        return this.baseRepository.findTDeptsByIdIn(ids);
    }

    @Override
    public List<TDept> findAllByRoleId(String roleId) {
        return this.baseRepository.findAllByRoleId(roleId);
    }

    @Override
    public List<TDept> findAllByUserId(String userId) {
        return this.baseRepository.findAllByUserId(userId);
    }
}
