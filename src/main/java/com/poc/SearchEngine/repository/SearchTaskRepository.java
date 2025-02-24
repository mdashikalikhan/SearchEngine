package com.poc.SearchEngine.repository;

import com.poc.SearchEngine.entity.SearchTask;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SearchTaskRepository extends JpaRepository<SearchTask, String> {
    @Transactional
    void deleteBySearchKey(String searchKey);
}
