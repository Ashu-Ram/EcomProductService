package com.scaler.productservice.dto;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductResponseDTO implements Serializable {
    // private UUID id;
    private int id;
    private String title;
    private double price;
    private String category;
    private String description;
    private String image;
}
