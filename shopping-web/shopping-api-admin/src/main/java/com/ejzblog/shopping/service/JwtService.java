package com.ejzblog.shopping.service;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.ejzblog.shopping.exceptionhandler.DataException;
import com.ejzblog.shopping.model.dto.AdminAccountDTO;
import com.google.common.base.Splitter;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.ejzblog.shopping.constant.Constant.SECRET;
import static com.ejzblog.shopping.enums.ResponseStatusEnum.TOKEN_EXPIRED;

/**
 * <p>
 * Description：
 * </p>
 *
 * @author Mango
 * @version v1.0.0
 * @date 2022-07-06 14:09
 * @see com.ejzblog.shopping.service
 */
@SuppressWarnings("ALL")
@Slf4j
public class JwtService {

    /**
     * 将盐 加密
     *
     * @return
     */
    private SecretKey generalKey() {
        // 本地的密码解码
        byte[] encodedKey = Base64.decodeBase64(SECRET);
        // 根据给定的字节数组使用AES加密算法构造一个密钥
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }

    /**
     * 计算Token时效性
     *
     * @return 时间戳
     */
    private Long calculatingTime() {
        // 获取当前系统时间
        long now = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(now);
        calendar.add(Calendar.HOUR, 24);
        // 计算15天之后的时间戳。
        return calendar.getTimeInMillis();
    }

    /**
     * 生成用户Token
     *
     * @param dto 当前用户信息
     * @return 字符串
     */
    public String createToken(AdminAccountDTO dto) {
        log.info("JwtService createToken parameter：{}", dto.toString());

        StringBuilder sb = new StringBuilder();

        sb.append("0,");

        // 计算Token时效性
        Long time = calculatingTime();
        sb.append(time).append(",");

        // 用户ID不可能为空
        sb.append(dto.getId()).append(",");

        // 昵称
        String nickName = dto.getNickName();
        sb.append(!StrUtil.isBlank(nickName) ? nickName : "").append(",");

        // 头像
        String avatar = dto.getAvatar();
        sb.append(!StrUtil.isBlank(avatar) ? avatar : "").append(",");

        // 账户类型
        Integer accountType = dto.getAccountType();
        sb.append(accountType).append(",");

        // 启用状态
        Integer useStatus = dto.getUseStatus();
        sb.append(useStatus).append(",");

        // 指定签名的时候使用的签名算法，也就是header那部分，jjwt已经将这部分内容封装好了。
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        // 生成JWT的时间
        Date now = new Date(time);
        // 生成签名的时候使用的秘钥secret，切记这个秘钥不能外露哦。它就是你服务端的私钥，在任何场景都不应该流露出去。
        // 一旦客户端得知这个secret, 那就意味着客户端是可以自我签发jwt了。
        SecretKey key = generalKey();
        JwtBuilder builder = null;

        try {
            /**
             * 下面就是在为payload添加各种标准声明和私有声明了：
             * 这里其实就是new一个JwtBuilder，设置jwt的body
             * iat: jwt的签发时间
             * sub(Subject)：代表这个JWT的主体，即它的所有人，这个是一个json格式的字符串，可以存放什么userid，roldid之类的，作为什么用户的唯一标志。
             * 设置签名使用的签名算法和签名使用的秘钥
             */
            builder = Jwts.builder().setIssuer("").setId(dto.getId().toString()).setIssuedAt(now).setSubject(sb.toString()).signWith(signatureAlgorithm, key);
        } catch (JWTCreationException exception) {
            //Invalid Signing configuration / Couldn't convert Claims.
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        if (builder != null) {
            return builder.compact();
        } else {
            throw new RuntimeException("生成 token 错误！");
        }
    }

    /**
     * 解析用户Token
     *
     * @param token
     * @return 用户信息
     */
    public AdminAccountDTO parseToken(final String token) {

        if (token == null || token.isEmpty()) {
            throw new DataException(TOKEN_EXPIRED.getMessage(), TOKEN_EXPIRED.getMessage());
        }

        SecretKey key = generalKey();

        /**
         * 得到DefaultJwtParser
         * 设置签名的秘钥
         * 设置需要解析的jwt
         */
        Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();

        if (claims.size() == 0) {
            throw new DataException(TOKEN_EXPIRED.getMessage(), TOKEN_EXPIRED.getMessage());
        }

        String subject = claims.getSubject();

        List<String> list = Splitter.on(",").splitToList(subject);

        long expired = Long.parseLong(list.get(1));

        if (System.currentTimeMillis() > expired) {
            log.info("token expired: {}", Long.valueOf(expired));
            throw new DataException(TOKEN_EXPIRED.getMessage(), TOKEN_EXPIRED.getMessage());
        }

        AdminAccountDTO dto = new AdminAccountDTO();

        // 用户ID
        Long id = Long.valueOf(list.get(2));
        dto.setId(id);

        // 昵称
        String nickName = list.get(3);
        dto.setNickName(nickName);

        // 头像
        String avatar = list.get(4);
        dto.setAvatar(avatar);

        // 用户状态
        Integer accountType = Integer.valueOf(list.get(5));
        dto.setAccountType(accountType);

        // 账号启用状态
        Integer useStatus = Integer.valueOf(list.get(6));
        dto.setUseStatus(useStatus);

        return dto;
    }

}
