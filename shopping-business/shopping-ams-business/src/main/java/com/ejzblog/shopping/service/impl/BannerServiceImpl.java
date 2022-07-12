package com.ejzblog.shopping.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ejzblog.shopping.domain.BannerDO;
import com.ejzblog.shopping.mapper.BannerMapper;
import com.ejzblog.shopping.model.dto.AdminAccountDTO;
import com.ejzblog.shopping.model.dto.BannerDTO;
import com.ejzblog.shopping.model.query.BannerQuery;
import com.ejzblog.shopping.model.req.BannerReq;
import com.ejzblog.shopping.pages.Pager;
import com.ejzblog.shopping.service.BannerService;
import com.ejzblog.shopping.util.CopyBeanUtilExt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.ejzblog.shopping.pages.PageUtil.convert;
import static com.ejzblog.shopping.pages.PageUtil.defaultObjWhenEmpty;

/**
 * <p>
 * Banner图 服务实现类
 * </p>
 *
 * @author Mango
 * @since 2022-07-05
 */
@SuppressWarnings("ALL")
@Slf4j
@Service
public class BannerServiceImpl extends ServiceImpl<BannerMapper, BannerDO> implements BannerService {

    @Autowired
    private BannerMapper bannerMapper;

    @Override
    public Pager<BannerDTO> getBannerList(BannerQuery query) {
        QueryWrapper<BannerDO> queryWrapper = new QueryWrapper<>();

        queryWrapper.lambda().eq(!StrUtil.isBlank(query.getTitle()),BannerDO::getTitle,query.getTitle());

        queryWrapper.lambda().orderByAsc(BannerDO::getSortOrder).orderByDesc(BannerDO::getId);

        Page<BannerDO> pages = bannerMapper.selectPage(new Page<BannerDO>(query.getPageCurrent(), query.getPageSize()), queryWrapper);

        if (pages.getRecords().isEmpty()) {
            return defaultObjWhenEmpty();
        }

        List<BannerDTO> list = pages.getRecords().stream()
                .map(this::doToDto)
                .sorted(Comparator.comparing(BannerDTO::getSortOrder).thenComparing(BannerDTO::getId).reversed())
                .collect(Collectors.toList());

        return convert(pages.getTotal(), pages.getCurrent(), pages.getSize(), list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Long create(BannerReq req, AdminAccountDTO operateUser) {
        BannerDO bannerDO = CopyBeanUtilExt.dtoToDo(req, BannerDO.class);
        
        bannerDO.setCreatorId(operateUser.getId());
        bannerDO.setCreatorName(operateUser.getNickName());
        
        bannerDO.setModifierId(operateUser.getId());
        bannerDO.setModifierName(operateUser.getNickName());

        bannerMapper.insert(bannerDO);
        
        return bannerDO.getId();
    }

    @Override
    public BannerDTO getDetailsById(Long id) {
        BannerDO oldBanner = bannerMapper.selectById(id);

        if (oldBanner != null) {
            return doToDto(oldBanner);
        }

        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Long updateById(BannerReq req, AdminAccountDTO operateUser) {
        BannerDO oldBanner = bannerMapper.selectById(req.getId());

        oldBanner.setTitle(req.getTitle());
        oldBanner.setBannerUrl(req.getBannerUrl());
        oldBanner.setPathType(req.getPathType());
        oldBanner.setPath(req.getPath());
        oldBanner.setSortOrder(req.getSortOrder());

        oldBanner.setModifierId(operateUser.getId());
        oldBanner.setModifierName(operateUser.getNickName());

        bannerMapper.updateById(oldBanner);

        return oldBanner.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void removeById(Long id) {
        bannerMapper.deleteById(id);
    }

    /**
     * DO 转换 DTO
     *
     * @param dos
     * @return
     */
    private BannerDTO doToDto(BannerDO dos) {
        BannerDTO dto = new BannerDTO();
        BeanUtils.copyProperties(dos, dto);
        return dto;
    }

}
