package cn.com.zy.admin.entity;

import cn.com.zy.entity.MybatisEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * <p>
 * 安全资源定义信息表
 * </p>
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("T_SECURITY")
@Entity
@Table(name = "T_SECURITY")
@Where(clause = "deleted = 0")
public class TSecurity extends MybatisEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    public TSecurity() {}
    public TSecurity(String id) { super(id); }
    /**
     * 所属微服务
     */
    @TableField("SERVICE_ID")
    @Column(name = "SERVICE_ID")
    private String serviceId;

    /**
     * 安全资源定义，如url
     */
    @TableField("SECURITY_DEF")
    @Column(name = "SECURITY_DEF")
    private String securityDef;
    /**
     * 是否为系统内置，系统内置资源不可编辑，用户自定义的可自由编辑
     */
    @TableField("FROM_SYSTEM")
    @Column(name = "FROM_SYSTEM")
    private Integer fromSystem;
    /**
     * 名称
     */
    @TableField("NAME")
    @Column(name = "NAME")
    private String name;

    /**
     * 描述
     */
    @TableField("REMARK")
    @Column(name = "REMARK")
    private String remark;

}
