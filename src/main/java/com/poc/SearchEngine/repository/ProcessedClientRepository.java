package com.poc.SearchEngine.repository;

import com.poc.SearchEngine.entity.ProcessedClient;
import com.poc.SearchEngine.entity.ProcessedClientId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessedClientRepository extends JpaRepository<ProcessedClient,
        ProcessedClientId> {
}
