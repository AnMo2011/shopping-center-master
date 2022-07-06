package com.ejzblog.shopping.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * <p>
 * Description：线程配置
 * </p>
 *
 * @author Mango
 * @version v1.0.0
 * @date 2022-07-05 11:39
 * @see com.ejzblog.shopping.config
 */
@SuppressWarnings("ALL")
@EnableAsync
@Configuration
public class TaskPoolConfig {

    /**
     * 线程池维护线程的最少数量
     */
    private final int corePoolSize = 5;

    /**
     * 线程池维护线程的最大数量
     */
    private final int maxPoolSize = 10;

    /**
     * 线程池维护线程所允许的空闲时间
     */
    private final int keepAliveTime = 0;

    /**
     * 线程池所使用的缓冲队列大小
     */
    private final int workQueueSize = 500;

    /**
     * 线程前缀
     */
    private final String namePrefix = "clicksExecutor-";

    @Bean("clicksExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setKeepAliveSeconds(keepAliveTime);
        executor.setQueueCapacity(workQueueSize);
        executor.setThreadNamePrefix(namePrefix);
        // 拒绝策略 提交线程直接执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

}
