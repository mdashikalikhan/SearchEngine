package com.poc.SearchEngine.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
public class AsyncConfig {
    @Bean(name = "branchTaskExecutor")
    public Executor branchTaskExecutor(){
        ThreadPoolTaskExecutor executor
                = new ThreadPoolTaskExecutor();

        executor.setCorePoolSize(100);
        executor.setMaxPoolSize(100);
        //executor.setQueueCapacity(200);
        executor.setThreadNamePrefix("BranchTaskThread-");
        executor.initialize();
        return executor;
    }
}
