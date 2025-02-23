package com.poc.SearchEngine.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "SEARCH_TASK")
@Data
public class SearchTask {
    @Id
    @Column(name = "SEARCH_KEY")
    private String searchKey;

    @Column(name = "start_time")
    private Date startTime;

    @Column(name = "end_time")
    private Date endTime;
}
