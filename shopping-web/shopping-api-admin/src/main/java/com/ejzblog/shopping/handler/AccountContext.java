package com.ejzblog.shopping.handler;

import com.ejzblog.shopping.model.dto.AdminAccountDTO;

/**
 * <p>
 * Description：
 * </p>
 *
 * @author Mango
 * @version v1.0.0
 * @date 2022-07-06 14:13
 * @see com.ejzblog.shopping.handler
 */
public class AccountContext {

    private static final ThreadLocal<AdminAccountDTO> LOCAL = new ThreadLocal<>();

    /**
     * 获取当前用户信息
     */
    public static AdminAccountDTO getUser() {
        return LOCAL.get();
    }

    /**
     * 插入当前用户信息
     *
     * @param dto
     */
    public static void setUser(AdminAccountDTO dto) {
        LOCAL.set(dto);
    }

    /**
     * 删除当前用户
     */
    public static void removeUser() {
        LOCAL.remove();
    }

    /**
     * 删除
     */
    public static void clear() {
        removeUser();
    }

}
