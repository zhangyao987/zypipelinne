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
 * 用户-角色中间表
 * </p>
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("R_USER_ROLE")
@Entity
@Table(name = "R_USER_ROLE")
@Where(clause = "deleted = 0")
public class RUserRole extends MybatisEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableField("USER_ID")
    @Column(name = "USER_ID")
    private String userId;

    /**
     * 角色ID
     */
    @TableField("ROLE_ID")
    @Column(name = "ROLE_ID")
    private String roleId;

}
