package com.scaler.productservice.repo;

import com.scaler.productservice.model.Product;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    Product findByTitle(String title);

    // List<Product> findAllByTitleContainingIgnoreCase(String title, Pageable pageable);

    Page<Product> findAllByTitleContainingIgnoreCase(String title, Pageable pageable);
    /*
    Select * from products where lower(title) = "iphone"
    //offset and limit.
     */

    //    Product findByTitleAndDescription(String title, String description);
    //    Product findByTitleOrDescription(String title, String description);

    //    Product findByPrice_amountLessThanEqual(double amount);// <=price
    //
    //    Product findByPriceLessThan(double price);//<price
    //
    //    Product findByPriceGreaterThanEqual(double price);// >= price

    //
    //    Product findByPriceGreaterThan(double price);//> price
    //
    //    Product findByPriceBetweenStartPriceAndEndPrice(double startPrice, double endPrice);
    @Query(value = CustomQueries.FIND_PRODUCT_BY_TITLE, nativeQuery = true)
    Product findProductByTitle(String title);
}
