package com.scaler.productservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Category extends BaseModel {

    private String categoryName;

    @OneToMany
    private List<Product> products;
}
