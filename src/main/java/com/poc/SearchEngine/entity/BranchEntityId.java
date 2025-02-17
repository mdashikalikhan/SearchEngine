package com.poc.SearchEngine.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class BranchEntityId implements Serializable {
    private Integer entityNum;
    private Long branchCode;
}
