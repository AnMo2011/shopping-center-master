package com.ejzblog.shopping.model.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * Description：
 * </p>
 *
 * @author Mango
 * @version v1.0.0
 * @date 2022-07-05 19:29
 * @see com.ejzblog.shopping.model.req
 */
@Data
@Accessors(chain = true)
@ApiModel("修改账户信息（入参）")
public class UpdateAccountInfoReq {

    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称", position = 100)
    private String nickName;

    /**
     * 头像
     */
    @ApiModelProperty(value = "头像", position = 200)
    private String avatar;

}
