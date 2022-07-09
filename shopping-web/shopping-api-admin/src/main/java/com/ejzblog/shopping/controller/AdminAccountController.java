package com.ejzblog.shopping.controller;

import com.ejzblog.shopping.annotation.TokenValidation;
import com.ejzblog.shopping.handler.AccountContext;
import com.ejzblog.shopping.model.dto.AdminAccountDTO;
import com.ejzblog.shopping.model.query.AdminAccountQuery;
import com.ejzblog.shopping.model.req.AccountReq;
import com.ejzblog.shopping.model.req.AdminAccountLoginReq;
import com.ejzblog.shopping.model.req.PasswordReq;
import com.ejzblog.shopping.model.req.UpdateAccountInfoReq;
import com.ejzblog.shopping.pages.Pager;
import com.ejzblog.shopping.result.ResponseResultDTO;
import com.ejzblog.shopping.service.AdminAccountService;
import com.ejzblog.shopping.service.JwtService;
import com.ejzblog.shopping.validation.group.UpdateAction;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
//@CrossOrigin
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

    /**
     * 创建
     *
     * @param req         参数
     * @param operateUser 当前操作用户信息
     * @return 主键ID
     */
    @TokenValidation
    @PostMapping(value = "/create")
    @ApiOperationSupport(order = 400)
    @ApiOperation(value = "创建（需要传token）")
    public ResponseResultDTO<Long> create(@RequestBody @Validated AccountReq req) {
        AdminAccountDTO dto = AccountContext.getUser();
        return ResponseResultDTO.success(adminAccountService.create(req, dto));
    }

    /**
     * PC端-修改密码
     *
     * @param req         参数
     * @param operateUser 当前操作用户信息
     * @return 主键ID
     */
    @TokenValidation
    @PutMapping(value = "/modify-password")
    @ApiOperationSupport(order = 500)
    @ApiOperation(value = "PC端-修改密码（需要传token）")
    public ResponseResultDTO<Long> modifyPassword(@RequestBody @Validated(UpdateAction.class) PasswordReq req) {
        AdminAccountDTO dto = AccountContext.getUser();
        return ResponseResultDTO.success(adminAccountService.modifyPassword(req, dto));
    }

    /**
     * PC端-修改用户基本信息（昵称、头像）
     *
     * @param req         参数
     * @param operateUser 当前操作用户信息
     * @return 主键
     */
    @TokenValidation
    @PutMapping(value = "/up-date-account-info")
    @ApiOperationSupport(order = 600)
    @ApiOperation(value = "PC端-修改用户基本信息（昵称、头像）（需要传token）")
    public ResponseResultDTO<Long> updateAccountInfo(@RequestBody UpdateAccountInfoReq req) {
        AdminAccountDTO dto = AccountContext.getUser();
        return ResponseResultDTO.success(adminAccountService.updateAccountInfo(req, dto));
    }

    /**
     * PC端-按照ID修改平台账户启用状态
     *
     * @param id 主键ID
     * @return 主键ID
     */
    @TokenValidation
    @PutMapping(value = "/update-account-use-status/{id}")
    @ApiOperationSupport(order = 700)
    @ApiOperation(value = "PC端-按照ID修改平台账户启用状态（需要传token）")
    public ResponseResultDTO<Long> updateAccountUseStatus(@PathVariable Long id) {
        return ResponseResultDTO.success(adminAccountService.updateAccountUseStatus(id));
    }

}
