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
 * 平台管理账户
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
@TableName(autoResultMap = true, value = "ams_admin_account")
public class AdminAccountDO extends AbstractBaseDO<AdminAccountDO> {

    private static final long serialVersionUID = -6983937234279137046L;

    /**
     * 账户（登录账号）
     */
    @TableField("account")
    private String account;

    /**
     * 密码
     */
    @TableField("password")
    private String password;

    /**
     * 昵称
     */
    @TableField("nick_name")
    private String nickName;

    /**
     * 头像
     */
    @TableField("avatar")
    private String avatar;

    /**
     * 账户类型
     */
    @TableField("account_type")
    private Integer accountType;

    /**
     * 启用状态：0->禁用；1->启用
     */
    @TableField("use_status")
    private Integer useStatus;

}
