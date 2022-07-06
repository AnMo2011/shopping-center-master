package com.ejzblog.shopping.controller;

import com.ejzblog.shopping.annotation.TokenValidation;
import com.ejzblog.shopping.handler.AccountContext;
import com.ejzblog.shopping.model.dto.AdminAccountDTO;
import com.ejzblog.shopping.model.query.AdminAccountQuery;
import com.ejzblog.shopping.model.req.AdminAccountLoginReq;
import com.ejzblog.shopping.pages.Pager;
import com.ejzblog.shopping.result.ResponseResultDTO;
import com.ejzblog.shopping.service.AdminAccountService;
import com.ejzblog.shopping.service.JwtService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 平台管理账户 前端控制器
 * </p>
 *
 * @author Mango
 * @since 2022-07-05
 */
@SuppressWarnings("ALL")
@ApiSort(100)
@Api(tags = "平台管理账户 前端控制器")
@RestController
@RequestMapping("/admin-account-do")
public class AdminAccountController {

    @Autowired
    private AdminAccountService adminAccountService;

    /**
     * jwt 服务
     */
    @Autowired
    private JwtService jwtService;

    /**
     * PC端-账户登录
     *
     * @param loginReq 参数
     * @return 平台账户基本信息 {@link AdminAccountDTO}
     */
    @PostMapping(value = "/login")
    @ApiOperationSupport(order = 100)
    @ApiOperation(value = "PC端登录")
    public ResponseResultDTO<String> login(@RequestBody @Validated AdminAccountLoginReq loginReq) {
        AdminAccountDTO dto = adminAccountService.login(loginReq);
        return ResponseResultDTO.success(jwtService.createToken(dto));
    }

    /**
     * 按照Token查询账户信息
     *
     * @param id 主键ID
     * @return 平台账户基本信息 {@link AdminAccountDTO}
     */
    @TokenValidation
    @GetMapping(value = "/get-account-info-by-token")
    @ApiOperationSupport(order = 200)
    @ApiOperation(value = "PC端-按照Token查询账户信息（需要传token）")
    public ResponseResultDTO<AdminAccountDTO> getAccountInfoByToken() {
        AdminAccountDTO dto = AccountContext.getUser();
        return ResponseResultDTO.success(adminAccountService.getAccountInfoByToken(dto.getId()));
    }

    /**
     * PC端-查询平台账户信息列表
     *
     * @param query 查询参数
     * @return 集合
     */
    @TokenValidation
    @GetMapping
    @ApiOperationSupport(order = 300)
    @ApiOperation(value = "PC端-查询平台账户信息列表（需要传token）")
    public ResponseResultDTO<Pager<AdminAccountDTO>> getAccountList(AdminAccountQuery query) {
        return ResponseResultDTO.success(adminAccountService.getAccountList(query));
    }

}
