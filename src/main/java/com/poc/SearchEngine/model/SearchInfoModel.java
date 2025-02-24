package com.poc.SearchEngine.model;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class SearchInfoModel {

    private String searchKey;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private int records;
}
