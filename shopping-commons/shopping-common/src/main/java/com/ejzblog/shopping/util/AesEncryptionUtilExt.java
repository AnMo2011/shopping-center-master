package com.ejzblog.shopping.util;

import com.google.common.io.BaseEncoding;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * <p>
 * Description：加密方式
 * </p>
 *
 * @author Mango
 * @version v1.0.0
 * @date 2022-07-06 14:53
 * @see com.ejzblog.shopping.util
 */
@SuppressWarnings("ALL")
@Slf4j
public class AesEncryptionUtilExt {

    /**
     * 加密 key
     */
    private static final String KEY = "CRQ5CoUsisbd841oxWD5vCIHlIQkx1Ae";

    /**
     * 加密方式
     */
    private static final String ENCRYPTION_WAY = "AES";

    /**
     * 加密
     *
     * @param input 参数
     * @return 加密之后的数据
     */
    public static String encryptByAES(String input) throws Exception {
        // Cipher：密码，获取加密对象
        // transformation:参数表示使用什么类型加密
        Cipher cipher = Cipher.getInstance(ENCRYPTION_WAY);
        // 指定秘钥规则
        // 第一个参数表示：密钥，key的字节数组 长度必须是16位
        // 第二个参数表示：算法
        SecretKeySpec sks = new SecretKeySpec(KEY.getBytes(), ENCRYPTION_WAY);
        // 对加密进行初始化
        // 第一个参数：表示模式，有加密模式和解密模式
        // 第二个参数：表示秘钥规则
        cipher.init(Cipher.ENCRYPT_MODE, sks);
        // 进行加密
        byte[] bytes = cipher.doFinal(input.getBytes());
        return BaseEncoding.base16().encode(bytes);
    }

    /**
     * 解密
     *
     * @param input 参数
     * @return 解密之后的数据
     */
    public static String decryptByAES(String input) throws Exception {
        // Cipher：密码，获取加密对象
        // transformation:参数表示使用什么类型加密
        Cipher cipher = Cipher.getInstance(ENCRYPTION_WAY);
        // 指定秘钥规则
        // 第一个参数表示：密钥，key的字节数组 长度必须是16位
        // 第二个参数表示：算法
        SecretKeySpec sks = new SecretKeySpec(KEY.getBytes(), ENCRYPTION_WAY);
        // 对加密进行初始化
        // 第一个参数：表示模式，有加密模式和解密模式
        // 第二个参数：表示秘钥规则
        cipher.init(Cipher.DECRYPT_MODE, sks);
        // 进行解密
        byte[] inputBytes = BaseEncoding.base16().decode(input.toUpperCase());
        byte[] bytes = cipher.doFinal(inputBytes);
        return new String(bytes);
    }

}
