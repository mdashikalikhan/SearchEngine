package com.poc.SearchEngine.config;

import java.util.concurrent.ForkJoinPool;

public class CustomForkJoinPool {
    private static final ForkJoinPool customPool = new ForkJoinPool(90); // 90 threads

    /*public static void executeWithCustomPool(Runnable task) {
        customPool.submit(task).join();
    }*/

    public static ForkJoinPool getCustomPool() {
        return customPool;
    }
}
