package com.poc.SearchEngine.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data public class SearchModel {
    @NotNull
    private String searchKey;
}
