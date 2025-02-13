package com.scaler.productservice.service;

import static com.scaler.productservice.mapper.ProductMapper.fakeProductResponseToProductResponse;

import com.scaler.productservice.client.FakeStoreAPIClient;
import com.scaler.productservice.dto.*;
import com.scaler.productservice.exception.ProductNotFoundException;
import com.scaler.productservice.model.Product;
import java.util.List;
import java.util.UUID;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("fakeStoreProductService")
public class FakeStoreProductServiceImpl implements ProductService {

    private final RestTemplateBuilder restTemplateBuilder;

    private final FakeStoreAPIClient fakeStoreAPIClient;
    private final RedisTemplate<String, FakeStoreProductResponseDTO> redisTemplate;

    public FakeStoreProductServiceImpl(
            RestTemplateBuilder restTemplateBuilder,
            FakeStoreAPIClient fakeStoreAPIClient,
            RedisTemplate<String, FakeStoreProductResponseDTO> redisTemplate) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.fakeStoreAPIClient = fakeStoreAPIClient;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public ProductListResponseDTO getAllProducts() {
        List<FakeStoreProductResponseDTO> fakeStoreProductResponseDTOS = fakeStoreAPIClient.getAllProducts();
        ProductListResponseDTO productListResponseDTO = new ProductListResponseDTO();
        // List<ProductResponseDTO> productResponseDTOS= new ArrayList<>();
        for (FakeStoreProductResponseDTO fakeStoreProductResponseDTO : fakeStoreProductResponseDTOS) {
            productListResponseDTO.getProducts().add(fakeProductResponseToProductResponse(fakeStoreProductResponseDTO));
        }

        return productListResponseDTO;
    }

    @Override
    public ProductResponseDTO getProductById(UUID id) throws ProductNotFoundException {
        return null;
    }

    @Override
    public ProductResponseDTO getProductById(int id) throws ProductNotFoundException {

        // USING REDIS
        // 1. Check if we have the product with the given id in the redis or not
        FakeStoreProductResponseDTO fakeStoreProductResponseDTO =
                (FakeStoreProductResponseDTO) redisTemplate.opsForHash().get("PRODUCTS", String.valueOf(id));
        if (fakeStoreProductResponseDTO != null) {
            // 2. if yes, return the product from Redis directly
            return fakeProductResponseToProductResponse(fakeStoreProductResponseDTO);
        }

        // 3.Else, make a call to FakStore and fetch the product
        fakeStoreProductResponseDTO = fakeStoreAPIClient.getProductById(id);

        // 4. Store in redis
        redisTemplate.opsForHash().put("PRODUCTS", String.valueOf(id), fakeStoreProductResponseDTO);

        // 5.Return the product
        return fakeProductResponseToProductResponse(fakeStoreProductResponseDTO);

        //        FakeStoreProductResponseDTO fakeStoreProductResponseDTO = fakeStoreAPIClient.getProductById(id);
        //        if (isNull(fakeStoreProductResponseDTO)) {
        //            throw new ProductNotFoundException("Product not found with id: " + id);
        //        }
        //
        //        return fakeProductResponseToProductResponse(fakeStoreProductResponseDTO);

        //        String getAllProductsURL="https://fakestoreapi.com/products/" +id;
        //
        //
        //        RestTemplate restTemplate= restTemplateBuilder.build();
        //        ResponseEntity<ProductResponseDTO> productResponse=
        // restTemplate.getForEntity(getAllProductsURL,ProductResponseDTO.class);
        //        return productResponse.getBody();
    }

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {
        // FakeStoreProductRequestDTO fakeStoreProductRequestDTO=
        // productRequestToFakeStoreProductRequest(productRequestDTO);
        //   FakeStoreProductResponseDTO fakeStoreProductDTO=
        // fakeStoreAPIClient.createProduct(fakeStoreProductRequestDTO);

        // return fakeProductResponseToProductResponse();
        return null;
    }

    @Override
    public boolean deleteProduct(int id) {
        String productDeleteURL = "https://fakestoreapi.com/products/" + id;

        RestTemplate restTemplate = restTemplateBuilder.build();
        restTemplate.delete(productDeleteURL);
        return true;
    }

    @Override
    public Product updateProduct(int id, Product updatedProduct) {
        return null;
    }

    @Override
    public ProductResponseDTO findProductByTitle(String title) {
        return null;
    }
}
