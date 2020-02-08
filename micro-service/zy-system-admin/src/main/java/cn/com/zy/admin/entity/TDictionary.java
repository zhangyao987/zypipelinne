package cn.com.zy.admin.entity;

import cn.com.zy.entity.MybatisEntity;
import cn.com.zy.util.enums.BooleanEnum;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;

/**
 * <p>
 * 业务字典（含业务字典分类）表
 * </p>
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("T_DICTIONARY")
@Entity
@Table(name = "T_DICTIONARY")
@Where(clause = "deleted = 0")
public class TDictionary extends MybatisEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 是否为系统字典，枚举BooleanEnum值
     */
    @Enumerated(EnumType.STRING)
    @TableField("SYSTEM_ONLY")
    @Column(name = "SYSTEM_ONLY")
    private BooleanEnum systemOnly;

    /**
     * 上级ID
     */
    @TableField("PARENT_ID")
    @Column(name = "PARENT_ID")
    private String parentId;

    /**
     * 类型，枚举值： 1：字典分类, 2：单级业务字典, 3：多级业务字典
     */
    @TableField("CATEGORY")
    @Column(name = "CATEGORY")
    private String category;

    /**
     * 字典分类ID
     */
    @TableField("TYPE")
    @Column(name = "TYPE")
    private String type;

    /**
     * 字典层级，从1开始，只对多级字典有效
     */
    @TableField("LEVEL")
    @Column(name = "LEVEL")
    private int level;

    /**
     * 是否为叶子节点，枚举值，只对多级字典有效
     */
    @Enumerated(EnumType.STRING)
    @TableField("IS_LEAF")
    @Column(name = "IS_LEAF")
    private BooleanEnum isLeaf;

    /**
     * 名称
     */
    @TableField("NAME")
    @Column(name = "NAME")
    private String name;

    /**
     * 规则码
     */
    @TableField("CODE")
    @Column(name = "CODE")
    private String code;

    /**
     * 序号，可调整该值，用于排序
     */
    @TableField("SORT_NUM")
    @Column(name = "SORT_NUM")
    private String sortNum;

    /**
     * 描述
     */
    @TableField("REMARK")
    @Column(name = "REMARK")
    private String remark;

}
