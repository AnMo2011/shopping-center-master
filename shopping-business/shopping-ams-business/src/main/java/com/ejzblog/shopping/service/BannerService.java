package com.ejzblog.shopping.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ejzblog.shopping.domain.BannerDO;
import com.ejzblog.shopping.model.dto.AdminAccountDTO;
import com.ejzblog.shopping.model.dto.BannerDTO;
import com.ejzblog.shopping.model.query.BannerQuery;
import com.ejzblog.shopping.model.req.BannerReq;
import com.ejzblog.shopping.pages.Pager;

/**
 * <p>
 * Banner图 服务类
 * </p>
 *
 * @author Mango
 * @since 2022-07-05
 */
public interface BannerService extends IService<BannerDO> {

    /**
     * 查询平台Banner图列表
     *
     * @param query 参数
     * @return 集合
     */
    Pager<BannerDTO> getBannerList(BannerQuery query);

    /**
     * 创建
     *
     * @param req         参数
     * @param operateUser 当前操作用户信息
     * @return 主键ID
     */
    Long create(BannerReq req, AdminAccountDTO operateUser);

    /**
     * 按照ID查询
     *
     * @param id 主键ID
     * @return 实体
     */
    BannerDTO getDetailsById(Long id);

    /**
     * 修改
     *
     * @param req         参数
     * @param operateUser 当前操作用户信息
     * @return 主键ID
     */
    Long updateById(BannerReq req, AdminAccountDTO operateUser);

    /**
     * 按照ID删除
     *
     * @param id 主键ID
     */
    void removeById(Long id);

}
