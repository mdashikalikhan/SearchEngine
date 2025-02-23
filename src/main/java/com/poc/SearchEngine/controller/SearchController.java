package com.poc.SearchEngine.controller;

import com.poc.SearchEngine.model.SearchModel;
import com.poc.SearchEngine.service.BranchProcessorService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/search")
@AllArgsConstructor
public class SearchController {

    private BranchProcessorService branchProcessorService;


    @PostMapping("/all")
    public String allBranchSearch(@Valid @RequestBody SearchModel searchModel) {
        String searchKey = searchModel.getSearchKey().toUpperCase();

        try {
            branchProcessorService.processAllBranches(searchKey);
            return "Search operation started for  key: " + searchKey;
        } catch (Exception e) {
            return "Error processing branches: " + e.getMessage();
        }
    }
}
