package com.ejzblog.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * <p>
 * 活动位
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
@TableName(autoResultMap = true, value ="ams_banner")
public class BannerDO extends AbstractBaseDO<BannerDO> {

    private static final long serialVersionUID = 3371787107951194875L;

    /**
     * 标题
     */
    @TableField("title")
    private String title;

    /**
     * 活动ID
     */
    @TableField("activity_id")
    private Long activityId;

    /**
     * Banner地址
     */
    @TableField("banner_url")
    private String bannerUrl;

    /**
     * 路径类型：0->默认不跳转；1->商品；2->APP页面；3->外部地址
     */
    @TableField("path_type")
    private Integer pathType;

    /**
     * 跳转路径
     */
    @TableField("path")
    private Integer path;

    /**
     * 活动状态：0->默认不是活动；1->活动开始；2->活动结束
     */
    @TableField("activity_status")
    private Integer activityStatus;

    /**
     * 活动开始时间
     */
    @TableField("activity_begin_time")
    private Date activityBeginTime;

    /**
     * 活动结束时间
     */
    @TableField("activity_end_time")
    private Date activityEndTime;

}
