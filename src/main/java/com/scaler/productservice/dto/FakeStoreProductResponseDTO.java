package com.scaler.productservice.dto;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductResponseDTO {
    private UUID id;
    private String title;
    private double price;
    private String category;
    private String description;
    private String image;
}
