package com.scaler.productservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel {

    private String title;
    private String description;
    private String image;

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
