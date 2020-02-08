package cn.com.zy.admin.entity.extend;

import cn.com.zy.util.enums.BooleanEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.annotations.OrderBy;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 业务字典（含业务字典分类）表
 * </p>
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Entity
@Table(name = "T_DICTIONARY")
@Where(clause = "DELETED = 0")
public class TDictionaryTree implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID", unique = true, length = 60)
    private String id;

    /**
     * 是否为系统字典，枚举BooleanEnum值
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "SYSTEM_ONLY")
    private BooleanEnum systemOnly;

    /**
     * 上级ID
     */
    @Column(name = "PARENT_ID")
    private String parentId;

    /**
     * 类型，枚举值： 1：字典分类, 2：单级业务字典, 3：多级业务字典
     */
    @Column(name = "CATEGORY")
    private String category;

    /**
     * 字典分类ID
     */
    @Column(name = "TYPE")
    private String type;

    /**
     * 字典层级，从1开始，只对多级字典有效
     */
    @Column(name = "LEVEL")
    private int level;

    /**
     * 是否为叶子节点，枚举值，只对多级字典有效
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "IS_LEAF")
    private BooleanEnum isLeaf;

    /**
     * 名称
     */
    @Column(name = "NAME")
    private String name;

    /**
     * 规则码
     */
    @Column(name = "CODE")
    private String code;

    /**
     * 序号，可调整该值，用于排序
     */
    @Column(name = "SORT_NUM")
    private String sortNum;

    /**
     * 描述
     */
    @Column(name = "REMARK")
    private String remark;

    @OneToMany(mappedBy = "parentId")
    @Where(clause="DELETED = 0")
    @OrderBy(clause="SORT_NUM ASC")
    List<TDictionaryTree> children;
}
