package com.ejzblog.shopping.pages;

import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 * Description：分页返回方法
 * </p>
 *
 * @author Mango
 * @version v1.0.0
 * @date 2022-03-28 11:21
 * @see com.ejzblog.shopping.pages
 */
@SuppressWarnings("ALL")
@Slf4j
public class PageUtil {

    /**
     * 分页返回
     *
     * @param total       总页数
     * @param pageCurrent 当前页
     * @param pageSize    每页条数
     * @param data        数据
     * @return {@link Pager}
     */
    public static <T> Pager<T> convert(Long total, Long pageCurrent, Long pageSize, List<T> data) {

        log.info("总页数：{}", total);
        log.info("当前页：{}", pageCurrent);
        log.info("每页条数：{}", pageSize);
        log.info("数据：{}", data);

        Pager<T> resultPage = new Pager();
        resultPage.setTotalCount(total);
        resultPage.setPageIndex(Math.toIntExact(pageCurrent));
        resultPage.setPageSize(Math.toIntExact(pageSize));
        resultPage.setList(data);
        return resultPage;
    }

    /**
     * 当列表为空的时候，可以直接返回这个
     *
     * @param <T>
     * @return
     */
    public static <T> Pager<T> defaultObjWhenEmpty() {
        Pager<T> resultPage = new Pager();
        resultPage.setList(Collections.emptyList());
        return resultPage;
    }

}
