package com.scaler.productservice.dto;

import com.scaler.productservice.model.SortParam;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SearchRequestDto {
    private String title;
    private int pageNumber;
    private int pageSize;
    private List<SortParam> sortParams;
}
