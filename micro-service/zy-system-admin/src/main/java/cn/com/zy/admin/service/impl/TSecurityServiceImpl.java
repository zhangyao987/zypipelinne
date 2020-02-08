package cn.com.zy.admin.service.impl;

import cn.com.zy.admin.dto.SecurityRoleDTO;
import cn.com.zy.admin.entity.TSecurity;
import cn.com.zy.admin.mapper.TSecurityMapper;
import cn.com.zy.admin.repository.TSecurityRepository;
import cn.com.zy.admin.service.ITSecurityService;
import cn.com.zy.service.impl.MybatisJpaServiceImpl;
import cn.com.zy.util.enums.Boolean;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 安全资源定义信息表 服务实现类
 * </p>
 *
 */
@Slf4j
@Service
public class TSecurityServiceImpl extends MybatisJpaServiceImpl<TSecurityMapper, TSecurityRepository, TSecurity> implements ITSecurityService {

    @Transactional(
            rollbackFor = {Exception.class}
    )
    @Override
    public void initAuthorities(List<TSecurity> entities) {
        Map<String, TSecurity> history = new HashMap<String, TSecurity>();
        // collect(Collectors.toList()) 最后一行一定要加，作为消费者消费数据，被消费，相关代码才会被执行
        this.baseMapper.selectList((new QueryWrapper<TSecurity>()).eq("FROM_SYSTEM", Boolean.TRUE.getKey())).stream().filter(s -> {
            history.put(s.getId(), s);
            return true;
        }).collect(Collectors.toList());
        entities.stream().filter(s -> {
            if (history.containsKey(s.getId())) {
                this.baseMapper.updateById(s);
                log.info("【更新】" + JSONObject.toJSONString(s));
                history.remove(s.getId());
                return true;
            } else {
                this.baseMapper.insert(s);
                log.info("【新增】" + JSONObject.toJSONString(s));
                return false;
            }
        }).collect(Collectors.toList());

        history.keySet().stream().map(s -> {
            this.baseMapper.deleteByIdWithFill(new TSecurity(s));
            log.info("【删除】" + JSONObject.toJSONString(history.get(s)));
            return true;
        }).collect(Collectors.toList());
    }

    @Override
    public List<TSecurity> findAllByMenuId(String menuId) {
        return this.baseRepository.findAllByMenuId(menuId);
    }

    @Override
    public Map<String, Set<String>> findAllSecurityRoleMapping() {
        Map<String, Set<String>> map = new HashMap<String, Set<String>>();
        List<SecurityRoleDTO> list = this.baseMapper.selectAllSecurityRoleDTO();
        list.forEach(item -> {
            if (map.get(item.getServiceId() + item.getSecurityDef()) == null) {
                map.put(item.getServiceId() + item.getSecurityDef(), new HashSet<String>());
            }
            map.get(item.getServiceId() + item.getSecurityDef()).addAll(item.getRoles());
        });
        return map;
    }
}
