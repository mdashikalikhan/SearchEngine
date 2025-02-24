package com.poc.SearchEngine.service;

import com.poc.SearchEngine.component.BranchLoader;
import com.poc.SearchEngine.config.CustomForkJoinPool;
import com.poc.SearchEngine.entity.SearchTask;
import com.poc.SearchEngine.repository.SearchTaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;

@Service
@AllArgsConstructor
public class BranchProcessorService {
    private BranchLoader branchLoader;
    private BranchService branchService;
    private SearchTaskRepository searchTaskRepository;
    private Executor branchTaskExecutor;

    @Async("branchTaskExecutor") // Run this method asynchronously
    public void processAllBranches(String searchKey) throws InterruptedException, ExecutionException {
        SearchTask searchTask = new SearchTask();
        searchTask.setSearchKey(searchKey);
        searchTask.setStartTime(LocalDateTime.now());
        searchTaskRepository.save(searchTask);

        List<Long> allBranchIds = branchLoader.getBranchIds(); // Get preloaded branch IDs
        /*int chunkSize = 100; // Each thread processes 100 branches
        List<CompletableFuture<Void>> futures = new ArrayList<>();

        for (int i = 0; i < allBranchIds.size(); i += chunkSize) {
            // Get a subset of branch IDs for this chunk
            List<Long> branchIdsChunk = allBranchIds.subList(i, Math.min(i + chunkSize, allBranchIds.size()));

            // Process each branch in the chunk
            for (Long branchId : branchIdsChunk) {
                CompletableFuture<Void> future = branchService.processBranchAsync(branchId, searchKey);
                futures.add(future);
            }
        }*/


        // Process branches in parallel using CompletableFuture
        /*List<CompletableFuture<Void>> futures = allBranchIds.stream()
                .map(branchId -> CompletableFuture.runAsync(() -> {
                    branchService.processBranchAsync(branchId, searchKey);
                }, branchTaskExecutor))
                .toList();
        // Wait for all threads to complete
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).get();*/

        // Process branches in parallel using a custom ForkJoinPool
        /*CustomForkJoinPool.executeWithCustomPool(() -> {
            allBranchIds.parallelStream().forEach(branchId -> {
                branchService.processBranchAsync(branchId, searchKey);
            });
        });*/

        ForkJoinPool customPool = CustomForkJoinPool.getCustomPool();
        /*List<CompletableFuture<Void>> futures = allBranchIds.stream()
                .map(branchId -> CompletableFuture.runAsync(() -> {
                    branchService.processBranchAsync(branchId, searchKey).join();
                }, customPool))
                .toList();*/

        /*List<CompletableFuture<Void>> futures = allBranchIds.stream()
                .map(branchId -> CompletableFuture.runAsync(() ->
                                branchService.processBranchAsync(branchId, searchKey) // Call the async method
                                        .join() // Ensure we wait for the async method to complete
                        , customPool))
                .toList();*/

        // Process branches in parallel with better chaining
        /*List<CompletableFuture<Void>> futures = allBranchIds.stream()
                .map(branchId -> CompletableFuture.supplyAsync(() -> branchService.processBranchAsync(branchId, searchKey), customPool)
                        .thenCompose(f -> f)) // Ensures complete async execution
                .toList();*/


        List<CompletableFuture<Void>> futures = allBranchIds.parallelStream()
                .map(branchId -> CompletableFuture.supplyAsync(() -> branchService.processBranchAsync(branchId, searchKey))
                        .thenCompose(f -> f))
                .toList();
        // Wait for all tasks to complete
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

        // Update the search task with end time
        searchTask.setEndTime(LocalDateTime.now());
        searchTaskRepository.save(searchTask);
    }
}
