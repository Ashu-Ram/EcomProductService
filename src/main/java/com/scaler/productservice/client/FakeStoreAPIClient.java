package com.scaler.productservice.client;

import com.scaler.productservice.dto.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/***
 * Wrapper on FakeStoreProduct APIs
 *
 *
 */
@Component
public class FakeStoreAPIClient {

    private final String fakeStoreAPIUrl;

    @Value("${fakestore.api.path.product}")
    private String fakeStoreProductsAPIPath;

    private final RestTemplateBuilder restTemplateBuilder;

    public FakeStoreAPIClient(
            RestTemplateBuilder restTemplateBuilder, @Value("${fakestore.api.url}") String fakeStoreAPIUrl) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.fakeStoreAPIUrl = fakeStoreAPIUrl;
    }

    public FakeStoreProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {
        String createProductURL = fakeStoreAPIUrl + fakeStoreProductsAPIPath;
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductResponseDTO> productResponse =
                restTemplate.postForEntity(createProductURL, productRequestDTO, FakeStoreProductResponseDTO.class);
        return productResponse.getBody();
    }

    public FakeStoreProductResponseDTO getProductById(int id) {
        String getProductByUrlId = fakeStoreAPIUrl + fakeStoreProductsAPIPath + "/" + id;
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductResponseDTO> productResponse =
                restTemplate.getForEntity(getProductByUrlId, FakeStoreProductResponseDTO.class);
        return productResponse.getBody();
    }

    public List<FakeStoreProductResponseDTO> getAllProducts() {

        String getAllProductsURL = fakeStoreAPIUrl + fakeStoreProductsAPIPath;

        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductResponseDTO[]> productResponseArray =
                restTemplate.getForEntity(getAllProductsURL, FakeStoreProductResponseDTO[].class);
        if (Objects.nonNull(productResponseArray.getBody())) {
            return List.of(productResponseArray.getBody());
        }
        return new ArrayList<>();
    }

    public boolean deleteProduct(int id) {
        String productDeleteURL = fakeStoreAPIUrl + fakeStoreProductsAPIPath + "/" + id;

        RestTemplate restTemplate = restTemplateBuilder.build();
        restTemplate.delete(productDeleteURL);
        return true;
    }
}
