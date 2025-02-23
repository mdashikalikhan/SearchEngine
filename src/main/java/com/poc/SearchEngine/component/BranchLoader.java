package com.poc.SearchEngine.component;

import com.poc.SearchEngine.entity.BranchEntity;
import com.poc.SearchEngine.repository.BranchRepository;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BranchLoader {
    @NonNull
    private BranchRepository branchRepository;

    private List<Long> branchIds;

    @PostConstruct
    public void loadAllBranchIds(){
        branchIds = branchRepository.findAll().stream().map(BranchEntity::getBranchCode)
                .toList();
        System.out.println(branchIds.size());
    }

    public List<Long> getBranchIds() {
        return branchIds;
    }
}
