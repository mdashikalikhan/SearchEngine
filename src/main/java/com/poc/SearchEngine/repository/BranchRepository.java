package com.poc.SearchEngine.repository;

import com.poc.SearchEngine.entity.BranchEntity;
import com.poc.SearchEngine.entity.BranchEntityId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchRepository extends JpaRepository<BranchEntity, BranchEntityId> {

}
