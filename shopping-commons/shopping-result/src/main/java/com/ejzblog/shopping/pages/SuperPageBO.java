package com.ejzblog.shopping.pages;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * Description：
 * </p>
 *
 * @author Mango
 * @version v1.0.0
 * @date 2022-05-19 14:06
 * @see com.ejzblog.shopping.pages
 */
@ApiModel("分页默认参数 vo")
@Getter
@Setter
public class SuperPageBO implements Serializable {

    private static final long serialVersionUID = 6688806995591529157L;

    /**
     * 当前页
     */
    @ApiModelProperty(value = "当前页（不传默认第一条）", position = 100, required = false)
    @JsonIgnore
    private Integer pageCurrent = 1;

    /**
     * 每页条数
     */
    @ApiModelProperty(value = "每页条数（不传默认每页20条）", position = 100, required = false)
    @JsonIgnore
    private Integer pageSize = 10;

}
