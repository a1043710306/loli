package cn.miraiapi.loli.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class ScheduleConfig {

    @Bean
    public ThreadPoolTaskExecutor taskExecutor(){
        ThreadPoolTaskExecutor taskExecutor=new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(Runtime.getRuntime().availableProcessors());
        // 设置最大线程数
        taskExecutor.setMaxPoolSize(Runtime.getRuntime().availableProcessors() * 10);
        // 设置队列容量
        taskExecutor.setQueueCapacity(Runtime.getRuntime().availableProcessors() * 10);
        // 设置线程活跃时间（秒）
        taskExecutor.setKeepAliveSeconds(10);
        // 设置默认线程名称
        taskExecutor.setThreadNamePrefix("pic-");
        // 设置拒绝策略
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 等待所有任务结束后再关闭线程池
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        return taskExecutor;
    }
}
