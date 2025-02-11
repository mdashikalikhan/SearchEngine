package com.poc.SearchEngine.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
public class AsyncConfig {
    @Bean(name = "branchTask")
    public Executor branchTaskExecutor(){
        ThreadPoolTaskExecutor executor
                = new ThreadPoolTaskExecutor();

        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(10);
        executor.setThreadNamePrefix("BranchTaskThread-");
        executor.initialize();
        return executor;
    }
}
