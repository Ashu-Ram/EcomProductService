package com.scaler.productservice.mapper;

import com.scaler.productservice.dto.*;
import com.scaler.productservice.model.Product;
import java.util.List;

public class ProductMapper {

    private ProductMapper() {}

    public static FakeStoreProductRequestDTO productRequestToFakeStoreProductRequest(
            ProductRequestDTO productRequestDTO) {
        FakeStoreProductRequestDTO fakeStoreProductRequestDTO = new FakeStoreProductRequestDTO();
        fakeStoreProductRequestDTO.setTitle(productRequestDTO.getTitle());
        fakeStoreProductRequestDTO.setPrice(productRequestDTO.getPrice());
        fakeStoreProductRequestDTO.setCategory(productRequestDTO.getCategory());
        fakeStoreProductRequestDTO.setDescription(productRequestDTO.getDescription());
        fakeStoreProductRequestDTO.setImage(productRequestDTO.getImage());

        return fakeStoreProductRequestDTO;
    }

    public static ProductResponseDTO fakeProductResponseToProductResponse(
            FakeStoreProductResponseDTO fakeStoreProductResponseDTO) {
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        productResponseDTO.setId(fakeStoreProductResponseDTO.getId());
        productResponseDTO.setPrice(fakeStoreProductResponseDTO.getPrice());
        productResponseDTO.setImage(fakeStoreProductResponseDTO.getImage());
        productResponseDTO.setDescription(fakeStoreProductResponseDTO.getDescription());
        productResponseDTO.setCategory(fakeStoreProductResponseDTO.getCategory());
        productResponseDTO.setTitle(fakeStoreProductResponseDTO.getTitle());

        return productResponseDTO;
    }

    public static ProductListResponseDTO convertProductListToProductListResponseDTO(List<Product> products) {
        ProductListResponseDTO productListResponseDTO = new ProductListResponseDTO();
        for (Product p : products) {
            ProductResponseDTO productResponseDTO = new ProductResponseDTO();
            // productResponseDTO.setId(p.getId());
            productResponseDTO.setImage(p.getImage());
            productResponseDTO.setTitle(p.getTitle());
            productResponseDTO.setPrice(p.getPrice().getAmount());
            productResponseDTO.setDescription(p.getDescription());
            productResponseDTO.setCategory(p.getCategory().getCategoryName());
            productListResponseDTO.getProducts().add(productResponseDTO);
        }
        return productListResponseDTO;
    }

    public static ProductResponseDTO convertProductToProductResponseDTO(Product p) {
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        // productResponseDTO.setId(p.getId());
        productResponseDTO.setImage(p.getImage());
        productResponseDTO.setTitle(p.getTitle());
        productResponseDTO.setPrice(p.getPrice().getAmount());
        productResponseDTO.setDescription(p.getDescription());
        productResponseDTO.setCategory(p.getCategory().getCategoryName());

        return productResponseDTO;
    }
}
