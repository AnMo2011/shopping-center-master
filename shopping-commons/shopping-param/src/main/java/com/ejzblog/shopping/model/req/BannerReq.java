package com.ejzblog.shopping.model.req;

import com.ejzblog.shopping.validation.group.CreateAction;
import com.ejzblog.shopping.validation.group.UpdateAction;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * Description：
 * </p>
 *
 * @author Mango
 * @version v1.0.0
 * @date 2022-07-11 17:33
 * @see com.ejzblog.shopping.model.req
 */
@Data
@Accessors(chain = true)
@ApiModel("banner图（入参）")
public class BannerReq {

    /**
     * 主键ID
     */
    @NotNull(message = "主键ID不能为空", groups = {UpdateAction.class})
    @ApiModelProperty(value = "主键ID", position = 100)
    private Long id;

    /**
     * 标题
     */
    @NotBlank(message = "标题不能为空", groups = {CreateAction.class})
    @ApiModelProperty(value = "标题", position = 200)
    private String title;

    /**
     * Banner地址
     */
    @NotBlank(message = "Banner地址不能为空", groups = {CreateAction.class})
    @ApiModelProperty(value = "Banner地址", position = 300)
    private String bannerUrl;

    /**
     * 路径类型：0->默认不跳转；1->商品；2->APP页面；3->外部地址
     */
    @ApiModelProperty(value = "路径类型：0->默认不跳转；1->商品；2->APP页面；3->外部地址", position = 400)
    private Integer pathType;

    /**
     * 跳转路径
     */
    @ApiModelProperty(value = "跳转路径", position = 500)
    private String path;

    /**
     * 数字越小 排序优先级越高 一级类目默认0
     */
    @ApiModelProperty(value = "数字越小 排序优先级越高 一级类目默认0", position = 500)
    private Integer sortOrder = 0;

}
