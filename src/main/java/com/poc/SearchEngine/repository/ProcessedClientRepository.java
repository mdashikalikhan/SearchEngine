package com.poc.SearchEngine.repository;

import com.poc.SearchEngine.entity.ProcessedClient;
import com.poc.SearchEngine.entity.ProcessedClientId;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProcessedClientRepository extends JpaRepository<ProcessedClient,
        ProcessedClientId> {
    @Query(value = "select count(1) from SEARCH_CLIENT where search_key=:searchKey", nativeQuery = true)
    int getTotalRecords(@Param("searchKey") String searchKey);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM SEARCH_CLIENT WHERE search_key = :searchKey", nativeQuery = true)
    void deleteAllBySearchKey(@Param("searchKey") String searchKey);
}
