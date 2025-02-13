package com.scaler.productservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
// @Document(indexName = "products")
// Document is elastic search=Row in mySQL
public class Product extends BaseModel {

    private String title;
    private String description;
    private String image;
    private int inventoryCount;

    @ManyToOne
    private Category category;

    @OneToOne
    private Price price;

    /*

    Product- Category : M:1
      1       1
      M       1
      M       1
     */
}
