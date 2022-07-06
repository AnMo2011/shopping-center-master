package com.ejzblog.shopping.util;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 * Description：
 * </p>
 *
 * @author Mango
 * @version v1.0.0
 * @date 2022-07-05 11:45
 * @see com.ejzblog.shopping.util
 */
@SuppressWarnings("ALL")
@Slf4j
public class GeneratedOrderUtilExt {


    /**
     * 锁对象，可以为任意对象
     */
    private static Object LOCK_OBJ = "lockerOrder";

    /**
     * 订单号生成计数器
     */
    private static long orderNumCount = 0L;

    /**
     * 每毫秒生成订单号数量最大值
     */
    private static int maxPerMSECSize = 1000;

    /**
     * 生成订单编号
     *
     * @return 订单号
     */
    public static synchronized String orderGenerated() {
        String orderCode = "";
        try {
            // 最终生成的订单号
            synchronized (LOCK_OBJ) {
                // 取系统当前时间作为订单号变量前半部分，精确到毫秒
                long nowLong = Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
                // 计数器到最大值归零，可扩展更大，目前1毫秒处理峰值1000个，1秒100万
                if (orderNumCount >= maxPerMSECSize) {
                    orderNumCount = 0L;
                }
                // 组装订单号
                String countStr = maxPerMSECSize + orderNumCount + "";
                orderCode = nowLong + countStr.substring(1);
                orderNumCount++;
            }
            log.info("本次生成的订单Code为：{}", orderCode);
        } catch (Exception e) {
            log.error("生成订单号错误为：{}", e.getMessage());
            e.printStackTrace();
        }
        return orderCode;
    }

}
