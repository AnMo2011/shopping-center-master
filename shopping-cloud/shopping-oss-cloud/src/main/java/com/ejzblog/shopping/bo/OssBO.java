package com.ejzblog.shopping.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * Description：
 * </p>
 *
 * @author Mango
 * @version v1.0.0
 * @date 2022-07-09 13:41
 * @see com.ejzblog.shopping.bo
 */
@Data
@Accessors(chain = true)
public class OssBO implements Serializable {

    private static final long serialVersionUID = 2600949834524196718L;

    /**
     * 外网
     */
    private String endpoint;

    /**
     * 阿里云API的密钥Access Key ID
     */
    private String accessKeyId;

    /**
     * 阿里云API的密钥Access Key Secret
     */
    private String accessKeySecret;

    /**
     * OSS地址
     */
    private String bucketAddress;

    /**
     * 阿里云API的文件夹名称
     */
    private String bucketName;

    /**
     * 默认 Oss 对象
     *
     * @param endpoint        外网
     * @param accessKeyId     阿里云API的密钥Access Key ID
     * @param accessKeySecret 阿里云API的密钥Access Key Secret
     * @param bucketAddress   OSS地址
     * @param bucketName      阿里云API的文件夹名称
     * @return
     */
    public OssBO defaultOssObj(String endpoint, String accessKeyId, String accessKeySecret,
                               String bucketAddress,
                               String bucketName) {

        setEndpoint(endpoint);
        setAccessKeyId(accessKeyId);
        setAccessKeySecret(accessKeySecret);
        setBucketAddress(bucketAddress);
        setBucketName(bucketName);

        return this;
    }

}
