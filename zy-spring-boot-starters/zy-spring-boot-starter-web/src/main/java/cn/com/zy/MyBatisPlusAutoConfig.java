package cn.com.zy;

import cn.com.zy.util.MybatisMetaObjectHandler;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.injector.methods.additional.LogicDeleteByIdWithFill;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.util.List;

/**
 * @description: <p>MyBatisPlus配置，详见：https://github.com/baomidou/mybatis-plus</p>
 */
@ConditionalOnProperty(name = "mybatis-plus.enabled", havingValue = "true")
public class MyBatisPlusAutoConfig {

    /**
     * @description: <p>MyBatisPlus 扫描配置,也可以使用@MapperScan("cn.com.taiji.*.mapper")替代</p>
     * @return: org.mybatis.spring.mapper.MapperScannerConfigurer
     */
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer scannerConfigurer = new MapperScannerConfigurer();
        scannerConfigurer.setBasePackage("cn.com.zy.*.mapper,cn.com.zy.*.*.mapper,cn.com.zy.*.*.*.mapper,cn.com.zy.*.*.*.*.mapper");
        return scannerConfigurer;
    }
    /**
     * @description: <p>分页配置</p>
     * @return: com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /**
     * @description: <p>设置 dev test 环境开启,SQL执行效率插件</p>
     * @return: com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor
     */
    @Bean
    @Profile({"dev","test"})
    public PerformanceInterceptor performanceInterceptor() {
        return new PerformanceInterceptor();
    }

    /**
     * @description: <p>实现数据逻辑删除</p>
     * @return: com.baomidou.mybatisplus.core.injector.ISqlInjector
     */
    @Bean
    @ConditionalOnProperty(name = "mybatis-plus.global-config.db-config.logic-delete-enabled", havingValue = "true")
    public ISqlInjector logicDeleteByIdWithFill() {
        return new LogicSqlInjector() {
            @Override
            public List<AbstractMethod> getMethodList() {
                List<AbstractMethod> methodList = super.getMethodList();
                methodList.add(new LogicDeleteByIdWithFill());
                return methodList;
            }
        };
    }
    /**
     * @description: <p>通用表结构字段值注入，如创建人、创建时间，最后修改人，最后修改时间</p>
     * @return: cn.com.taiji.admin.util.mybatisplus.MybatisMetaObjectHandler
     */
    @Bean
    @ConditionalOnProperty(name = "mybatis-plus.global-config.db-config.auto-fill-enabled", havingValue = "true")
    public MybatisMetaObjectHandler metaObjectHandler() {
        return new MybatisMetaObjectHandler();
    }
}
