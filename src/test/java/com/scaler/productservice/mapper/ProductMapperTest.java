package com.scaler.productservice.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.scaler.productservice.dto.ProductListResponseDTO;
import com.scaler.productservice.dto.ProductResponseDTO;
import com.scaler.productservice.model.Category;
import com.scaler.productservice.model.Price;
import com.scaler.productservice.model.Product;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class ProductMapperTest {

    @Test
    void testConvertProductListToProductListResponseDTO() {
        // Prepare test data
        Category category = new Category();
        category.setCategoryName("Electronics");

        Price price = new Price();
        price.setAmount(1000);
        price.setDiscount(0);
        price.setCurrency("INR");

        Product product1 = new Product();
        product1.setTitle("Laptop");
        product1.setDescription("Best Laptop");
        product1.setImage("image_url");
        product1.setCategory(category);
        product1.setPrice(price);

        Product product2 = new Product();
        product2.setTitle("Phone");
        product2.setDescription("Best Phone");
        product2.setImage("phone_image_url");
        product2.setCategory(category);
        product2.setPrice(price);
        List<Product> productList = Arrays.asList(product1, product2);

        // Call the method to test
        ProductListResponseDTO productListResponseDTO =
                ProductMapper.convertProductListToProductListResponseDTO(productList);

        // Assert that the responseDTO is not null
        assertNotNull(productListResponseDTO);
        assertEquals(2, productListResponseDTO.getProducts().size());

        // Validate the first product in the list
        ProductResponseDTO firstProduct = productListResponseDTO.getProducts().get(0);
        assertNotNull(firstProduct);
        assertEquals(product1.getTitle(), firstProduct.getTitle());
        assertEquals(product1.getDescription(), firstProduct.getDescription());
        assertEquals(product1.getImage(), firstProduct.getImage());
    }
}
