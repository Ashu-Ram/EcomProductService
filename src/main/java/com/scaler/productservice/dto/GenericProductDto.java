package com.scaler.productservice.dto;

import com.scaler.productservice.model.Category;
import com.scaler.productservice.model.Product;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericProductDto {
    private UUID id;
    private String title;
    private int price;
    private Category category;
    private String description;
    private String image;
    private int inventoryCount;

    public static GenericProductDto from(Product product) {
        GenericProductDto genericProductDto = new GenericProductDto();
        genericProductDto.setTitle(product.getTitle());
        genericProductDto.setDescription(product.getDescription());
        genericProductDto.setInventoryCount(product.getInventoryCount());
        // genericProductDto.setPrice(product.getPrice());
        genericProductDto.setImage(product.getImage());
        genericProductDto.setId(product.getId());
        genericProductDto.setCategory((product.getCategory()));

        return genericProductDto;
    }
}
