package com.ejzblog.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * APP版本详情
 * </p>
 *
 * @author Mango
 * @since 2022-07-05
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@TableName(autoResultMap = true, value = "ams_app_version_item")
public class AppVersionItemDO extends AbstractBaseDO<AppVersionItemDO> {

    private static final long serialVersionUID = 3071753869683998984L;

    /**
     * app ID
     */
    @TableField("app_version_id")
    private Long appVersionId;

    /**
     * 更新跳转路径
     */
    @TableField("jump_url")
    private String jumpUrl;

    /**
     * 更新提示语
     */
    @TableField("update_reminder")
    private String updateReminder;

    /**
     * 更新说明
     */
    @TableField("update_instruction")
    private String updateInstruction;

}
