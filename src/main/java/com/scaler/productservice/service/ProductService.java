package com.scaler.productservice.service;

import com.scaler.productservice.dto.ProductListResponseDTO;
import com.scaler.productservice.dto.ProductRequestDTO;
import com.scaler.productservice.dto.ProductResponseDTO;
import com.scaler.productservice.exception.ProductNotFoundException;
import com.scaler.productservice.model.Product;
import java.util.UUID;

public interface ProductService {

    ProductListResponseDTO getAllProducts();

    ProductResponseDTO getProductById(int id) throws ProductNotFoundException;

    ProductResponseDTO getProductById(UUID id) throws ProductNotFoundException;

    ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO);

    boolean deleteProduct(int id);

    Product updateProduct(int id, Product updatedProduct);

    ProductResponseDTO findProductByTitle(String title) throws ProductNotFoundException;
}
