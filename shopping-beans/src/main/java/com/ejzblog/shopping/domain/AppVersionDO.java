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
 * APP版本
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
@TableName(autoResultMap = true, value = "ams_app_version")
public class AppVersionDO extends AbstractBaseDO<AppVersionDO> {

    private static final long serialVersionUID = -4770298280950451425L;

    /**
     * 账户类型：0->移动端；1->商家端
     */
    @TableField("account_type")
    private Integer accountType;

    /**
     * 平台类型： 0->ios；1->android；2->微信小程序
     */
    @TableField("platform_type")
    private Integer platformType;

    /**
     * 版本号
     */
    @TableField("versions")
    private String versions;

}
