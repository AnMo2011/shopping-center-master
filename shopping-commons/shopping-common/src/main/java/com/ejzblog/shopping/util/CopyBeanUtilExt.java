package com.ejzblog.shopping.util;

import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

/**
 * <p>
 * Description：
 * </p>
 *
 * @author Mango
 * @version v1.0.0
 * @date 2022-07-05 11:41
 * @see com.ejzblog.shopping.util
 */
@SuppressWarnings("ALL")
@Slf4j
public class CopyBeanUtilExt<Dto, Do> {

    /**
     * DTO 转换为 DO
     *
     * @param dtoEntity dto
     * @param doClass   do
     * @param <Do>      do
     * @return do
     */
    public static <Do> Do dtoToDo(Object dtoEntity, Class<Do> doClass) {
        if (dtoEntity == null) {
            log.info("dtoEntity 结果为 null");
            return null;
        }
        try {
            Do newInstance = doClass.newInstance();
            BeanUtils.copyProperties(dtoEntity, newInstance);
            // Dto转换Do
            if (ObjectUtil.isEmpty(newInstance)) {
                log.info("dtoEntity 结果为 null");
            }
            return newInstance;
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * DO 转换为 DTO
     *
     * @param doEntity
     * @param dtoClass
     * @param <Dto>
     * @return
     */
    public static <Dto> Dto doToDto(Object doEntity, Class<Dto> dtoClass) {
        if (doEntity == null) {
            log.info("doEntity 结果为 null");
            return null;
        }
        try {
            Dto newInstance = dtoClass.newInstance();
            // DTO 转换 DO
            BeanUtils.copyProperties(doEntity, newInstance);
            if (ObjectUtil.isEmpty(newInstance)) {
                log.info("doEntity 结果为 null");
                return null;
            }
            return newInstance;
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

}
