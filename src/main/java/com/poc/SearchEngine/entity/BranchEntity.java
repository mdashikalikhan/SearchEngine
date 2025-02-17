package com.poc.SearchEngine.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "MBRN")
@Data
public class BranchEntity {
    private Integer entityNum;
    @Id
    @Column(name="MBRN_CODE", updatable = false)
    private Long branchCode;
    @Column(name="MBRN_NAME", updatable = false)
    private String branchName;
}
