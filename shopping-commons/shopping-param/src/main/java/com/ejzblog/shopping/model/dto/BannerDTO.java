package com.ejzblog.shopping.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * Description：
 * </p>
 *
 * @author Mango
 * @version v1.0.0
 * @date 2022-07-11 17:42
 * @see com.ejzblog.shopping.model.dto
 */
@Data
@ToString
@Accessors(chain = true)
@ApiModel("banner图（出参）")
public class BannerDTO implements Serializable {

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID", position = 100)
    private Long id;

    /**
     * 标题
     */
    @ApiModelProperty(value = "标题", position = 200)
    private String title;

    /**
     * 活动ID
     */
    @ApiModelProperty(value = "活动ID", position = 300)
    private Long activityId;

    /**
     * Banner地址
     */
    @ApiModelProperty(value = "Banner地址", position = 400)
    private String bannerUrl;

    /**
     * 路径类型：0->默认不跳转；1->商品；2->APP页面；3->外部地址
     */
    @ApiModelProperty(value = "路径类型：0->默认不跳转；1->商品；2->APP页面；3->外部地址", position = 500)
    private Integer pathType;

    /**
     * 跳转路径
     */
    @ApiModelProperty(value = "跳转路径", position = 600)
    private String path;

    /**
     * 活动状态：0->默认不是活动；1->活动开始；2->活动结束
     */
    @ApiModelProperty(value = "活动状态：0->默认不是活动；1->活动开始；2->活动结束", position = 700)
    private Integer activityStatus;

    /**
     * 活动开始时间
     */
    @ApiModelProperty(value = "活动开始时间", position = 800)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date activityBeginTime;

    /**
     * 活动结束时间
     */
    @ApiModelProperty(value = "活动结束时间", position = 900)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date activityEndTime;

    /**
     * 数字越小 排序优先级越高 一级类目默认0
     */
    @ApiModelProperty(value = "数字越小 排序优先级越高 一级类目默认0", position = 1000)
    private Integer sortOrder;

}
