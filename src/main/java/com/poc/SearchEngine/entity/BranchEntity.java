package com.poc.SearchEngine.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "MBRN")
@IdClass(BranchEntityId.class)
@Data
public class BranchEntity {
    @Id
    @Column(name="MBRN_CODE", updatable = false)
    private Integer entityNum;
    @Id
    @Column(name="MBRN_CODE", updatable = false)
    private Long branchCode;
    @Column(name="MBRN_NAME", updatable = false)
    private String branchName;
}
