package com.ejzblog.shopping.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ejzblog.shopping.domain.AdminAccountDO;
import com.ejzblog.shopping.exceptionhandler.DataException;
import com.ejzblog.shopping.mapper.AdminAccountMapper;
import com.ejzblog.shopping.model.dto.AdminAccountDTO;
import com.ejzblog.shopping.model.query.AdminAccountQuery;
import com.ejzblog.shopping.model.req.AccountReq;
import com.ejzblog.shopping.model.req.AdminAccountLoginReq;
import com.ejzblog.shopping.model.req.PasswordReq;
import com.ejzblog.shopping.model.req.UpdateAccountInfoReq;
import com.ejzblog.shopping.pages.Pager;
import com.ejzblog.shopping.service.AdminAccountService;
import com.ejzblog.shopping.util.AesEncryptionUtilExt;
import com.ejzblog.shopping.util.CopyBeanUtilExt;
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

import static com.ejzblog.shopping.constant.Constant.USER_STATUS_DISABLED;
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
        queryWrapper.lambda().eq(AdminAccountDO::getAccount, loginReq.getUsername());

        AdminAccountDO oldAccountDO = adminAccountMapper.selectOne(queryWrapper);

        if (oldAccountDO == null) {
            // 账号或密码错误
            throw new DataException(INCORRECT_USERNAME_OR_PASSWORD.getMessage(), INCORRECT_USERNAME_OR_PASSWORD.getMessage());
        }

        // 启用状态：0->禁用；1->启用
        if (Objects.equals(oldAccountDO.getUseStatus(), USER_STATUS_DISABLED)) {
            // 账号禁用
            throw new DataException(ACCOUNT_IS_DISABLED.getMessage(), ACCOUNT_IS_DISABLED.getMessage());
        }

        try {
            String pwd = AesEncryptionUtilExt.encryptByAES(loginReq.getPassword());

            if (pwd.equals(oldAccountDO.getPassword())) {
                return doToDto(oldAccountDO);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // 账号或密码错误
        throw new DataException(INCORRECT_USERNAME_OR_PASSWORD.getMessage(), INCORRECT_USERNAME_OR_PASSWORD.getMessage());
    }

    @Override
    public AdminAccountDTO getAccountInfoByToken(Long id) {
        AdminAccountDO oldAccountDO = adminAccountMapper.selectById(id);

        if (oldAccountDO != null) {
            return doToDto(oldAccountDO);
        }

        return null;
    }

    @Override
    public Pager<AdminAccountDTO> getAccountList(AdminAccountQuery query) {
        QueryWrapper<AdminAccountDO> queryWrapper = new QueryWrapper<>();

        queryWrapper.lambda().like(!StrUtil.isBlank(query.getAccount()),AdminAccountDO::getAccount,query.getAccount());

        queryWrapper.lambda().eq(AdminAccountDO::getAccountType, 10);
        queryWrapper.lambda().orderByDesc(AdminAccountDO::getId);

        Page<AdminAccountDO> pages = adminAccountMapper.selectPage(new Page<AdminAccountDO>(query.getPageCurrent(), query.getPageSize()), queryWrapper);

        if (pages.getRecords().isEmpty()) {
            return defaultObjWhenEmpty();
        }

        List<AdminAccountDTO> list = pages.getRecords().stream()
                .map(this::doToDto)
                .sorted(Comparator.comparing(AdminAccountDTO::getId).reversed())
                .collect(Collectors.toList());

        return convert(pages.getTotal(), pages.getCurrent(), pages.getSize(), list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Long create(AccountReq req, AdminAccountDTO operateUser) {
        AdminAccountDO adminAccountDO = CopyBeanUtilExt.dtoToDo(req, AdminAccountDO.class);

        adminAccountDO.setCreatorId(operateUser.getId());
        adminAccountDO.setCreatorName(operateUser.getNickName());

        adminAccountDO.setModifierId(operateUser.getId());
        adminAccountDO.setModifierName(operateUser.getNickName());

        adminAccountMapper.insert(adminAccountDO);

        return adminAccountDO.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Long modifyPassword(PasswordReq req, AdminAccountDTO operateUser) {
        AdminAccountDO oldAccountOD = adminAccountMapper.selectById(operateUser.getId());

        try {
            oldAccountOD.setPassword(AesEncryptionUtilExt.encryptByAES(req.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        adminAccountMapper.updateById(oldAccountOD);

        return oldAccountOD.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Long updateAccountInfo(UpdateAccountInfoReq req, AdminAccountDTO operateUser) {
        AdminAccountDO oldAccountOD = adminAccountMapper.selectById(operateUser.getId());

        oldAccountOD.setNickName(req.getNickName());
        oldAccountOD.setAvatar(req.getAvatar());

        adminAccountMapper.updateById(oldAccountOD);

        return oldAccountOD.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Long updateAccountUseStatus(Long id) {
        AdminAccountDO oldAccountOD = adminAccountMapper.selectById(id);

        if (Objects.equals(oldAccountOD.getUseStatus(), USER_STATUS_ENABLE)) {
            oldAccountOD.setUseStatus(USER_STATUS_DISABLED);
        } else {
            oldAccountOD.setUseStatus(USER_STATUS_ENABLE);
        }

        adminAccountMapper.updateById(oldAccountOD);

        return oldAccountOD.getId();
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
