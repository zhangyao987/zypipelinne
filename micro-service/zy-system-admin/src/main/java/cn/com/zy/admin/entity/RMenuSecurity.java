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
 * 菜单-安全资源中间表
 * </p>
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("R_MENU_SECURITY")
@Entity
@Table(name = "R_MENU_SECURITY")
@Where(clause = "deleted = 0")
public class RMenuSecurity extends MybatisEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单ID
     */
    @TableField("MENU_ID")
    @Column(name = "MENU_ID")
    private String menuId;

    /**
     * 安全资源ID
     */
    @TableField("SECURITY_ID")
    @Column(name = "SECURITY_ID")
    private String securityId;

}
