package com.dia.app.support.task;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author: wuhua.wwh
 * @Date: 2018/4/27 22:50
 * @Description:
 **/
@Configuration
@EnableAsync
public class CommonThreadPool {

  private int corePoolSize = 10;//线程池维护线程的最少数量

  private int maxPoolSize = 30;//线程池维护线程的最大数量

  private int queueCapacity = 8; //缓存队列

  private int keepAlive = 60;//允许的空闲时间

  @Bean
  public Executor commonExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(corePoolSize);
    executor.setMaxPoolSize(maxPoolSize);
    executor.setQueueCapacity(queueCapacity);
    executor.setThreadNamePrefix("mqExecutor-");
    executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy()); //对拒绝task的处理策略
    executor.setKeepAliveSeconds(keepAlive);
    executor.initialize();
    return executor;
  }
}
