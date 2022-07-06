package com.ejzblog.shopping.pages;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * <p>
 * Description：分页DTO
 * </p>
 *
 * @author Mango
 * @version v1.0.0
 * @date 2022-03-28 11:21
 * @see com.ejzblog.shopping.pages
 */
@Data
@Accessors(chain = true)
public class Pager<T> {

    /**
     * 当前页内容
     */
    @ApiModelProperty(value = "当前页内容", position = 100)
    private List<T> list;

    /**
     * 当前页码
     */
    @ApiModelProperty(value = "当前页码", position = 200)
    private int pageIndex = 0;

    /**
     * 每页显示数据 ,默认显示10天记录
     */
    @ApiModelProperty(value = "每页显示数据 ,默认显示10天记录", position = 300)
    private int pageSize = 0;

    /**
     * 总记录数
     */
    @ApiModelProperty(value = "总记录数", position = 400)
    private long totalCount = 0;

    /**
     * 总页面数
     */
    @ApiModelProperty(value = "总页面数", position = 500)
    private long totalPageCount = 0;

    public Pager() {

    }

    public Pager(Integer pageIndex, Integer pageSize) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }


    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
        resetTotalPageCount();
    }


    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
        resetTotalPageCount();
    }


    private void resetTotalPageCount() {
        if (pageSize > 0 && totalCount > 0) {
            totalPageCount = (long) Math.ceil(totalCount * 1.0 / pageSize);
        }
    }

}
