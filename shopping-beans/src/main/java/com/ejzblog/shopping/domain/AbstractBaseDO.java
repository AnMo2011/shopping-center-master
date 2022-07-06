package com.ejzblog.shopping.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * Description：
 * </p>
 *
 * @author Mango
 * @version v1.0.0
 * @date 2022-07-05 16:55
 * @see com.ejzblog.shopping.domain
 */
@Getter
@Setter
@ToString
public abstract class AbstractBaseDO<T extends Model<T>> extends Model<T> implements Serializable {

    private static final long serialVersionUID = 5856087739325251476L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 创建人ID
     */
    @TableField(value = "creator_id")
    private Long creatorId;

    /**
     * 创建人昵称
     */
    @TableField(value = "creator_name")
    private String creatorName;

    /**
     * 创建时间
     */
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;

    /**
     * 修改人ID
     */
    @TableField(value = "modifier_id")
    private Long modifierId;

    /**
     * 修改人昵称
     */
    @TableField(value = "modifier_name")
    private String modifierName;

    /**
     * 修改时间
     */
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    /**
     * 逻辑删除(0正常1删除)
     */
    @TableLogic(value = "0", delval = "1")
    @TableField(value = "is_deleted")
    private Integer isDeleted;

    /**
     * 版本
     */
    @Version
    @TableField(value = "version")
    private Integer version;

}
