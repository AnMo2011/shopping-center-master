package com.ejzblog.shopping.model.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * Description：
 * </p>
 *
 * @author Mango
 * @version v1.0.0
 * @date 2022-07-08 15:48
 * @see com.ejzblog.shopping.model.req
 */
@Data
@Accessors(chain = true)
@ApiModel("管理员帐户登录（入参）")
public class AccountReq {

    /**
     * 账户（登录账号）
     */
    @NotBlank(message = "账号不能为空")
    @ApiModelProperty(value = "账户（登录账号）", position = 200)
    private String account;

    /**
     * 昵称
     */
    @NotBlank(message = "昵称不能为空")
    @ApiModelProperty(value = "昵称", position = 300)
    private String nickName;

    /**
     * 头像
     */
    @NotBlank(message = "头像不能为空")
    @ApiModelProperty(value = "头像", position = 400)
    private String avatar;

}
