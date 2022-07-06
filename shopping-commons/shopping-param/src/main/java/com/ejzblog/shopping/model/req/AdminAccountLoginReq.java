package com.ejzblog.shopping.model.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * Description：管理员帐户登录
 * </p>
 *
 * @author Mango
 * @version v1.0.0
 * @date 2022-07-05 17:53
 * @see com.ejzblog.shopping.model.dto
 */
@Data
@Accessors(chain = true)
@ApiModel("管理员帐户登录（入参）")
public class AdminAccountLoginReq {

    /**
     * 账号
     */
    @NotBlank(message = "账号不能为空")
    @ApiModelProperty(value = "账号", position = 100)
    private String account;

    /**
     * 密码
     */
    @NotBlank(message = "登录密码不能为空")
    @ApiModelProperty(value = "密码", position = 200)
    private String password;

}
