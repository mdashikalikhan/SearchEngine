package com.poc.SearchEngine.controller;

import com.poc.SearchEngine.entity.SearchTask;
import com.poc.SearchEngine.model.SearchInfoModel;
import com.poc.SearchEngine.model.SearchModel;
import com.poc.SearchEngine.service.BranchProcessorService;
import com.poc.SearchEngine.service.BranchService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/search")
@AllArgsConstructor
public class SearchController {

    private BranchProcessorService branchProcessorService;

    private BranchService branchService;


    @GetMapping("/info/{searchKey}")
    public ResponseEntity<SearchInfoModel> getSearchInformation(@PathVariable("searchKey") String searchKey){
        String sKey = searchKey.toUpperCase();
        return ResponseEntity.ofNullable(branchService.getSearchInfo(sKey));
    }

    @DeleteMapping("/{searchKey}")
    public ResponseEntity<Object> deleteSearch(@PathVariable("searchKey") String searchKey){
        String sKey = searchKey.toUpperCase();
        branchService.deleteSearch(sKey);
        return ResponseEntity.ok("Delete Search: " + searchKey);
    }
    @PostMapping("/customer")
    public ResponseEntity<String> allBranchSearch(@Valid @RequestBody SearchModel searchModel) {
        String searchKey = searchModel.getSearchKey().toUpperCase();

        SearchTask searchTask = branchService.getSearchTask(searchKey);
        if(searchTask!=null){
            if(searchTask.getEndTime()==null){
                return ResponseEntity.status(HttpStatus.ALREADY_REPORTED)
                        .body("Search Operation is already started for key: " + searchKey);
            } else{
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body("Search Operation is completed for key: " + searchKey);
            }
        }

        try {
            branchProcessorService.processAllBranches(searchKey);
            return ResponseEntity.ok( "Search operation started for  key: " + searchKey);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error processing branches: " + e.getMessage());

        }
    }
}
