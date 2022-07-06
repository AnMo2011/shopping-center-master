package com.ejzblog.shopping.model.req;

import com.ejzblog.shopping.validation.group.UpdateAction;
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
 * @date 2022-07-05 18:01
 * @see com.ejzblog.shopping.model.req
 */
@Data
@Accessors(chain = true)
@ApiModel("修改密码参数（入参）")
public class PasswordReq {

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空", groups = {UpdateAction.class})
    @ApiModelProperty(value = "密码", position = 100)
    private String password;

}
