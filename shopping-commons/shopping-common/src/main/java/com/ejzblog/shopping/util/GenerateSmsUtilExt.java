package com.ejzblog.shopping.util;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * Description：
 * </p>
 *
 * @author Mango
 * @version v1.0.0
 * @date 2022-07-05 11:42
 * @see com.ejzblog.shopping.util
 */
@SuppressWarnings("ALL")
@Slf4j
public class GenerateSmsUtilExt {

    /**
     * 锁对象，可以为任意对象
     */
    private static Object LOCK_OBJ = "lockerOrder";

    /**
     * 生成随机验证码
     *
     * @param count
     * @return
     */
    public static synchronized String smsGeneratedCode() {
        String code = "";
        try {
            synchronized (LOCK_OBJ) {
                code = (int) ((Math.random() * 9 + 1) * Math.pow(10, 6 - 1)) + "";
            }
            log.info("本次生成的订单Code为：{}", code);
        } catch (Exception e) {
            log.error("生成短信验证码错误为：{}", e.getMessage());
            e.printStackTrace();
        }
        return code;
    }

}

