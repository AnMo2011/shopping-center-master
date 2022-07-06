package com.ejzblog.shopping.service;

import com.ejzblog.shopping.domain.AdminAccountDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ejzblog.shopping.model.dto.AdminAccountDTO;
import com.ejzblog.shopping.model.query.AdminAccountQuery;
import com.ejzblog.shopping.model.req.AdminAccountLoginReq;
import com.ejzblog.shopping.model.req.PasswordReq;
import com.ejzblog.shopping.model.req.UpdateAccountInfoReq;
import com.ejzblog.shopping.pages.Pager;

/**
 * <p>
 * 平台管理账户 服务类
 * </p>
 *
 * @author Mango
 * @since 2022-07-05
 */
public interface AdminAccountService extends IService<AdminAccountDO> {

    /**
     * PC端-账户登录
     *
     * @param loginReq 参数
     * @return 平台账户基本信息 {@link AdminAccountDTO}
     */
    AdminAccountDTO login(AdminAccountLoginReq loginReq);

    /**
     * 按照Token查询账户信息
     * @param id 主键ID
     * @return 平台账户基本信息 {@link AdminAccountDTO}
     */
    AdminAccountDTO getAccountInfoByToken(Long id);

    /**
     * PC端-查询平台账户信息列表
     *
     * @param query 查询参数
     * @return 集合
     */
    Pager<AdminAccountDTO> getAccountList(AdminAccountQuery query);

    /**
     * PC端-修改密码
     *
     * @param req         参数
     * @param operateUser 当前操作用户信息
     * @return 主键ID
     */
    Long modifyPassword(PasswordReq req, AdminAccountDTO operateUser);

    /**
     * PC端-修改用户基本信息（昵称、头像）
     *
     * @param req         参数
     * @param operateUser 当前操作用户信息
     * @return 主键
     */
    Long updateAccountInfo(UpdateAccountInfoReq req, AdminAccountDTO operateUser);

    /**
     * PC端-按照ID修改平台账户启用状态
     *
     * @param id 主键ID
     * @return 主键ID
     */
    Long updateAccountUseStatus(Long id);

}
