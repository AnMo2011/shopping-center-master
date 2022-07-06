package com.ejzblog.shopping.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ejzblog.shopping.domain.AdminAccountDO;
import com.ejzblog.shopping.exceptionhandler.DataException;
import com.ejzblog.shopping.mapper.AdminAccountMapper;
import com.ejzblog.shopping.model.dto.AdminAccountDTO;
import com.ejzblog.shopping.model.query.AdminAccountQuery;
import com.ejzblog.shopping.model.req.AdminAccountLoginReq;
import com.ejzblog.shopping.model.req.PasswordReq;
import com.ejzblog.shopping.model.req.UpdateAccountInfoReq;
import com.ejzblog.shopping.pages.Pager;
import com.ejzblog.shopping.service.AdminAccountService;
import com.ejzblog.shopping.util.AesEncryptionUtilExt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.ejzblog.shopping.constant.Constant.USER_STATUS_ENABLE;
import static com.ejzblog.shopping.enums.ResponseStatusEnum.ACCOUNT_IS_DISABLED;
import static com.ejzblog.shopping.enums.ResponseStatusEnum.INCORRECT_USERNAME_OR_PASSWORD;
import static com.ejzblog.shopping.pages.PageUtil.convert;
import static com.ejzblog.shopping.pages.PageUtil.defaultObjWhenEmpty;

/**
 * <p>
 * 平台管理账户 服务实现类
 * </p>
 *
 * @author Mango
 * @since 2022-07-05
 */
@SuppressWarnings("ALL")
@Slf4j
@Service
public class AdminAccountServiceImpl extends ServiceImpl<AdminAccountMapper, AdminAccountDO> implements AdminAccountService {

    @Autowired
    private AdminAccountMapper adminAccountMapper;

    @Override
    public AdminAccountDTO login(AdminAccountLoginReq loginReq) {
        QueryWrapper<AdminAccountDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(AdminAccountDO::getAccount, loginReq.getAccount());

        AdminAccountDO oldAccountDO = adminAccountMapper.selectOne(queryWrapper);

        if (oldAccountDO != null) {

            // 启用状态：0->禁用；1->启用
            if (Objects.equals(oldAccountDO.getUseStatus(), USER_STATUS_ENABLE)) {

                try {
                    String pwd = AesEncryptionUtilExt.encryptByAES(loginReq.getPassword());

                    if(pwd.equals(oldAccountDO.getPassword())) {
                        return doToDto(oldAccountDO);
                    }

                    // 账号或密码错误
                    throw new DataException(INCORRECT_USERNAME_OR_PASSWORD.getMessage(),INCORRECT_USERNAME_OR_PASSWORD.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            // 账号禁用
            throw new DataException(ACCOUNT_IS_DISABLED.getMessage(),ACCOUNT_IS_DISABLED.getMessage());
        }

        // 账号或密码错误
        throw new DataException(INCORRECT_USERNAME_OR_PASSWORD.getMessage(),INCORRECT_USERNAME_OR_PASSWORD.getMessage());
    }

    @Override
    public AdminAccountDTO getAccountInfoByToken(Long id) {
        AdminAccountDO oldAccountDO = adminAccountMapper.selectById(id);

        if(oldAccountDO != null){
            return doToDto(oldAccountDO);
        }

        return null;
    }

    @Override
    public Pager<AdminAccountDTO> getAccountList(AdminAccountQuery query) {
        QueryWrapper<AdminAccountDO> queryWrapper = new QueryWrapper<>();

        queryWrapper.lambda().orderByDesc(AdminAccountDO::getId);

        Page<AdminAccountDO> pages = adminAccountMapper.selectPage(new Page<AdminAccountDO>(query.getPageCurrent(), query.getPageSize()), queryWrapper);

        if(pages.getRecords().isEmpty()){
           return defaultObjWhenEmpty();
        }

        List<AdminAccountDTO> list = pages.getRecords().stream()
                .map(this::doToDto)
                .sorted(Comparator.comparing(AdminAccountDTO::getId).reversed())
                .collect(Collectors.toList());

        return convert(pages.getTotal(),pages.getCurrent(),pages.getSize(),list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Long modifyPassword(PasswordReq req, AdminAccountDTO operateUser) {
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Long updateAccountInfo(UpdateAccountInfoReq req, AdminAccountDTO operateUser) {
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Long updateAccountUseStatus(Long id) {
        return null;
    }

    /**
     * DO 转换 DTO
     *
     * @param dos
     * @return
     */
    private AdminAccountDTO doToDto(AdminAccountDO dos) {
        AdminAccountDTO dto = new AdminAccountDTO();
        BeanUtils.copyProperties(dos, dto);
        return dto;
    }

}
