package com.ejzblog.shopping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>
 * Description：
 * </p>
 *
 * @author Mango
 * @version v1.0.0
 * @date 2022-07-05 16:35
 * @see com.ejzblog.shopping
 */
@Slf4j
@SpringBootApplication(scanBasePackages = "com.ejzblog.*")
public class ApiAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiAdminApplication.class, args);
        log.info("{} ---> 启动成功", "shopping-api-admin");
    }

}
