package com.ejzblog.shopping.controller;

import com.ejzblog.shopping.annotation.TokenValidation;
import com.ejzblog.shopping.handler.AccountContext;
import com.ejzblog.shopping.model.dto.AdminAccountDTO;
import com.ejzblog.shopping.model.dto.BannerDTO;
import com.ejzblog.shopping.model.query.BannerQuery;
import com.ejzblog.shopping.model.req.BannerReq;
import com.ejzblog.shopping.pages.Pager;
import com.ejzblog.shopping.result.ResponseResultDTO;
import com.ejzblog.shopping.service.BannerService;
import com.ejzblog.shopping.validation.group.CreateAction;
import com.ejzblog.shopping.validation.group.UpdateAction;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * Banner图 前端控制器
 * </p>
 *
 * @author Mango
 * @since 2022-07-05
 */
@SuppressWarnings("ALL")
@Slf4j
@ApiSort(300)
@Api(tags = "Banner图 前端控制器")
@RestController
@RequestMapping("/banner-do")
public class BannerController {

    @Autowired
    private BannerService bannerService;

    /**
     * PC端-查询平台Banner图列表
     *
     * @param query 查询参数
     * @return 集合
     */
    @TokenValidation
    @GetMapping
    @ApiOperationSupport(order = 100)
    @ApiOperation(value = "PC端-查询平台Banner图列表（需要传token）")
    public ResponseResultDTO<Pager<BannerDTO>> getBannerList(BannerQuery query) {
        return ResponseResultDTO.success(bannerService.getBannerList(query));
    }

    /**
     * 创建
     *
     * @param req 参数
     * @return 主键ID
     */
    @TokenValidation
    @PostMapping
    @ApiOperationSupport(order = 200)
    @ApiOperation(value = "创建（需要传token）")
    public ResponseResultDTO<Long> create(@RequestBody @Validated(CreateAction.class) BannerReq req) {
        AdminAccountDTO dto = AccountContext.getUser();
        return ResponseResultDTO.success(bannerService.create(req, dto));
    }

    /**
     * 按照ID查询
     *
     * @param id 主键ID
     * @return 实体
     */
    @TokenValidation
    @GetMapping(value = "/{id}")
    @ApiOperationSupport(order = 300)
    @ApiOperation(value = "按照ID查询")
    public ResponseResultDTO<BannerDTO> getDetailsById(@PathVariable Long id) {
        return ResponseResultDTO.success(bannerService.getDetailsById(id));
    }

    /**
     * 修改
     *
     * @param req 参数
     * @return 主键ID
     */
    @TokenValidation
    @PutMapping
    @ApiOperationSupport(order = 400)
    @ApiOperation(value = "修改")
    public ResponseResultDTO<Long> updateById(@RequestBody @Validated(UpdateAction.class) BannerReq req) {
        AdminAccountDTO dto = AccountContext.getUser();
        return ResponseResultDTO.success(bannerService.updateById(req, dto));
    }

    /**
     * 按照ID删除
     *
     * @param id 主键ID
     */
    @TokenValidation
    @DeleteMapping(value = "/{id}")
    @ApiOperationSupport(order = 500)
    @ApiOperation(value = "修改")
    public ResponseResultDTO<Void> removeById(@PathVariable Long id) {
        bannerService.removeById(id);
        return ResponseResultDTO.success();
    }

}
