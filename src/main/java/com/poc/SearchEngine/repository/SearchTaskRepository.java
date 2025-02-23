package com.poc.SearchEngine.repository;

import com.poc.SearchEngine.entity.SearchTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SearchTaskRepository extends JpaRepository<SearchTask, String> {
}
