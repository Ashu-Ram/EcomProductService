package com.scaler.productservice.repo;

import com.scaler.productservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    Product findByTitle(String title);

    Product findByTitleAndDescription(String title, String description);

    Product findByTitleOrDescription(String title, String description);

    Product findByPriceLessThanEqual(double price);// <=price

    Product findByPriceLessThan(double price);//<price

    Product findByPriceGreaterThanEqual(double price);// >= price

    Product findByPriceGreaterThan(double price);//> price

    Product findByPriceBetweenStartPriceAndEndPrice(double startPrice, double endPrice);
}
