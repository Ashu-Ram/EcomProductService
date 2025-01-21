package com.scaler.EcomProductService.service;

import com.scaler.EcomProductService.client.FakeStoreAPIClient;
import com.scaler.EcomProductService.dto.*;
import com.scaler.EcomProductService.exception.ProductNotFoundException;
import com.scaler.EcomProductService.mapper.ProductMapper;
import com.scaler.EcomProductService.model.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static com.scaler.EcomProductService.mapper.ProductMapper.fakeProductResponseToProductResponse;
import static com.scaler.EcomProductService.mapper.ProductMapper.productRequestToFakeStoreProductRequest;

import static com.scaler.EcomProductService.util.ProductUtils.isNull;


@Service("fakeStoreProductService")
public class FakeStoreProductServiceImpl implements  ProductService{

    private RestTemplateBuilder restTemplateBuilder;

    private FakeStoreAPIClient fakeStoreAPIClient;


    public FakeStoreProductServiceImpl(RestTemplateBuilder restTemplateBuilder, FakeStoreAPIClient fakeStoreAPIClient) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.fakeStoreAPIClient = fakeStoreAPIClient;
    }

    @Override
    public ProductListResponseDTO getAllProducts() {
        List<FakeStoreProductResponseDTO> fakeStoreProductResponseDTOS=fakeStoreAPIClient.getAllProducts();

        ProductListResponseDTO productListResponseDTO  = new ProductListResponseDTO();
        List<ProductResponseDTO> productResponseDTOS= new ArrayList<>();
        for(FakeStoreProductResponseDTO fakeStoreProductResponseDTO:fakeStoreProductResponseDTOS)
        {
            productListResponseDTO.getProducts().add(fakeProductResponseToProductResponse(fakeStoreProductResponseDTO));
        }

        return productListResponseDTO ;
    }

    @Override
    public ProductResponseDTO getProductById(int id) throws ProductNotFoundException {

      FakeStoreProductResponseDTO fakeStoreProductResponseDTO= fakeStoreAPIClient.getProductById(id);
if(isNull(fakeStoreProductResponseDTO)){
    throw new ProductNotFoundException("Product not found with id: "+ id);
}

      return fakeProductResponseToProductResponse(fakeStoreProductResponseDTO);


//        String getAllProductsURL="https://fakestoreapi.com/products/" +id;
//
//
//        RestTemplate restTemplate= restTemplateBuilder.build();
//        ResponseEntity<ProductResponseDTO> productResponse= restTemplate.getForEntity(getAllProductsURL,ProductResponseDTO.class);
//        return productResponse.getBody();
    }

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {
       // FakeStoreProductRequestDTO fakeStoreProductRequestDTO= productRequestToFakeStoreProductRequest(productRequestDTO);
     //   FakeStoreProductResponseDTO fakeStoreProductDTO= fakeStoreAPIClient.createProduct(fakeStoreProductRequestDTO);

       // return fakeProductResponseToProductResponse();
        return null;
    }


    @Override
    public boolean deleteProduct(int id) {
        String productDeleteURL="https://fakestoreapi.com/products/"+id;

        RestTemplate restTemplate= restTemplateBuilder.build();
         restTemplate.delete(productDeleteURL);
        return true;
    }

    @Override
    public Product updateProduct(int id, Product updatedProduct) {
        return null;
    }
}
