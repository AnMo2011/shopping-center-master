package com.ejzblog.shopping.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ejzblog.shopping.config.MybatisRedisCache;
import com.ejzblog.shopping.domain.AdminAccountDO;
import org.apache.ibatis.annotations.CacheNamespace;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 平台管理账户 Mapper 接口
 * </p>
 *
 * @author Mango
 * @since 2022-07-05
 */
@Repository
@CacheNamespace(implementation = MybatisRedisCache.class, eviction = MybatisRedisCache.class)
public interface AdminAccountMapper extends BaseMapper<AdminAccountDO> {

}
