package com.ejzblog.shopping.service;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.ejzblog.shopping.bo.OssBO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * <p>
 * Description：
 * </p>
 *
 * @author Mango
 * @version v1.0.0
 * @date 2022-07-09 13:16
 * @see com.ejzblog.shopping.service
 */
@SuppressWarnings("ALL")
@Slf4j
@Component
public class OssService {

    /**
     * 外网
     */
    @Value("${oss.endpoint}")
    public String endpoint;

    /**
     * 阿里云API的密钥Access Key ID
     */
    @Value("${oss.access_key_id}")
    public String accessKeyId;

    /**
     * 阿里云API的密钥Access Key Secret
     */
    @Value("${oss.access_key_secret}")
    public String accessKeySecret;

    /**
     * OSS地址
     */
    @Value("${oss.bucket_address}")
    public String bucketAddress;

    /**
     * 阿里云API的文件夹名称
     */
    @Value("${oss.bucket_name}")
    public String bucketName;

    @PostConstruct
    private void init() {
        new Thread(this::reload).start();
    }

    private static OssBO ossBO = null;

    private OssBO reload() {
        log.info("开始加载 OSS 参数");

        if (ossBO != null) {
            return ossBO;
        }

        ossBO = new OssBO().defaultOssObj(endpoint, accessKeyId, accessKeySecret, bucketAddress, bucketName);

        log.info("加载完成 OSS 参数");
        return ossBO;
    }

    /**
     * 初始化OSS
     *
     * @return {@link OSS}
     */
    private static OSS ossInit() {
        return new OSSClientBuilder().build(ossBO.getEndpoint(), ossBO.getAccessKeyId(), ossBO.getAccessKeySecret());
    }

    /**
     * 通过文件上传
     *
     * @param multipartFile 文件
     * @return 图片地址
     */
    public static String uploadOss(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        String newName = System.currentTimeMillis() + "." + suffix;
        OSS client = ossInit();
        try {
            client.putObject(new PutObjectRequest(ossBO.getBucketName(), newName, new ByteArrayInputStream(file.getBytes())));
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        } finally {
            client.shutdown();
        }
        log.info("流上传返回地址为：", ossBO.getBucketAddress() + newName);
        return ossBO.getBucketAddress() + newName;
    }

    /**
     * 删除OSS图片地址
     *
     * @param url 图片地址
     */
    public static void deleteOss(String url) {
        log.info("删除OSS文件地址为：{}", url);

        OSS client = ossInit();

        String key = url.substring(url.lastIndexOf("/") + 1);

        client.deleteObject(ossBO.getBucketName(), "" + key);
        // 关闭OSSClient。
        client.shutdown();

        log.info("OSS文件删除成功");
    }

}
