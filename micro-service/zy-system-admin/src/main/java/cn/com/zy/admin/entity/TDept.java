package cn.com.zy.admin.entity;

import cn.com.zy.entity.MybatisEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 部门信息表
 * </p>
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("T_DEPT")
@Entity
@Table(name = "T_DEPT")
@Where(clause = "deleted = 0")
public class TDept extends MybatisEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 上级部门
     */
    @TableField("PARENT_ID")
    @Column(name = "PARENT_ID")
    private String parentId;

    /**
     * 机构序号，可调整该值，用于排序
     */
    @TableField("SORT_NUM")
    @Column(name = "SORT_NUM")
    private String sortNum;

    /**
     * 机构类型，取自数据字典
     */
    @TableField("TYPE")
    @Column(name = "TYPE")
    private String type;

    /**
     * 机构名称
     */
    @TableField("NAME")
    @Column(name = "NAME")
    private String name;

    /**
     * 机构描述
     */
    @TableField("REMARK")
    @Column(name = "REMARK")
    private String remark;

    @TableField(exist = false)
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "PARENT_ID")
    private List<TDept> children = new ArrayList<TDept>();

}
