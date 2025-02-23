package com.poc.SearchEngine.service;

import com.poc.SearchEngine.component.BranchLoader;
import com.poc.SearchEngine.entity.SearchTask;
import com.poc.SearchEngine.repository.SearchTaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@AllArgsConstructor
public class BranchProcessorService {
    private BranchLoader branchLoader;
    private BranchService branchService;
    private SearchTaskRepository searchTaskRepository;

    @Async("branchTaskExecutor") // Run this method asynchronously
    public void processAllBranches(String searchKey) throws InterruptedException, ExecutionException {
        SearchTask searchTask = new SearchTask();
        searchTask.setSearchKey(searchKey);
        searchTask.setStartTime(new Date());
        searchTaskRepository.save(searchTask);

        List<Long> allBranchIds = branchLoader.getBranchIds(); // Get preloaded branch IDs
        int chunkSize = 100; // Each thread processes 100 branches
        List<CompletableFuture<Void>> futures = new ArrayList<>();

        for (int i = 0; i < allBranchIds.size(); i += chunkSize) {
            // Get a subset of branch IDs for this chunk
            List<Long> branchIdsChunk = allBranchIds.subList(i, Math.min(i + chunkSize, allBranchIds.size()));

            // Process each branch in the chunk
            for (Long branchId : branchIdsChunk) {
                CompletableFuture<Void> future = branchService.processBranchAsync(branchId, searchKey);
                futures.add(future);
            }
        }

        // Wait for all threads to complete
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).get();

        // Update the search task with end time
        searchTask.setEndTime(new Date());
        searchTaskRepository.save(searchTask);
    }
}
