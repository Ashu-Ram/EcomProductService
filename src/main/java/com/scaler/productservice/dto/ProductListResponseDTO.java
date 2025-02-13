package com.scaler.productservice.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductListResponseDTO {

    private List<ProductResponseDTO> products;

    public ProductListResponseDTO() {
        this.products = new ArrayList<>();
    }
}
