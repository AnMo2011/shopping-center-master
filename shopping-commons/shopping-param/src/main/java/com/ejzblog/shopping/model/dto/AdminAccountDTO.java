package com.ejzblog.shopping.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * Description：
 * </p>
 *
 * @author Mango
 * @version v1.0.0
 * @date 2022-07-05 17:57
 * @see com.ejzblog.shopping.model.dto
 */
@Data
@ToString
@Accessors(chain = true)
@ApiModel("平台账户基本信息（出参）")
public class AdminAccountDTO implements Serializable {

    private static final long serialVersionUID = -862109955896106789L;

    @ApiModelProperty(value = "主键ID", position = 100)
    private Long id;

    /**
     * 账户（登录账号）
     */
    @ApiModelProperty(value = "账户（登录账号）", position = 200)
    private String account;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称", position = 300)
    private String nickName;

    /**
     * 头像
     */
    @ApiModelProperty(value = "头像", position = 400)
    private String avatar;

    /**
     * 账户类型
     */
    @ApiModelProperty(value = "账户类型", position = 500)
    private Integer accountType;

    /**
     * 启用状态：0->禁用；1->启用
     */
    @ApiModelProperty(value = "启用状态：0->禁用；1->启用）", position = 600)
    private Integer useStatus;

}
