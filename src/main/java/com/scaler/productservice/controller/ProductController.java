package com.scaler.productservice.controller;

import com.scaler.productservice.dto.ProductListResponseDTO;
import com.scaler.productservice.dto.ProductRequestDTO;
import com.scaler.productservice.dto.ProductResponseDTO;
import com.scaler.productservice.exception.ProductNotFoundException;
import com.scaler.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    private final ProductService productService; // immutable

    @Autowired // Autowired for constructor injection is Optional from spring 4+ version
    public ProductController(@Qualifier("productService") ProductService productService) {
        this.productService = productService;
    }

    /**
     * Field Injection
     * @Autowired
     * @Qualifier("fakeStoreProductService")
     * private ProductService productService;
     */
    @GetMapping("/products")
    public ResponseEntity getAllProducts() {

        ProductListResponseDTO response = productService.getAllProducts();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity getProductById(@PathVariable("id") int id) throws ProductNotFoundException {
        ProductResponseDTO response = productService.getProductById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/products/title/{title}")
    public ResponseEntity getProductByTitle(@PathVariable("title") String title) throws ProductNotFoundException {
        ProductResponseDTO response = productService.findProductByTitle(title);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/products")
    public ResponseEntity createProduct(@RequestBody ProductRequestDTO productRequestDTO) {
        ProductResponseDTO responseDTO = productService.createProduct(productRequestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity deleteProductById(@PathVariable("id") int id) {
        boolean response = productService.deleteProduct(id);
        return ResponseEntity.ok(response);
    }
}
