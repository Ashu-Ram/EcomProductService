package com.scaler.productservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import java.util.List;
import lombok.Data;

@Data
@Entity(name = "ECOM_ORDER")
public class Order extends BaseModel {

    @ManyToMany
    private List<Product> products;
}

/*
Product Order
 1       M
 M       1

 Product -Order M:M
 */
