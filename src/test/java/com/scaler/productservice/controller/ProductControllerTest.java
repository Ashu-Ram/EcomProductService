package com.scaler.productservice.controller;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.scaler.productservice.dto.ProductListResponseDTO;
import com.scaler.productservice.dto.ProductRequestDTO;
import com.scaler.productservice.dto.ProductResponseDTO;
import com.scaler.productservice.exception.ProductNotFoundException;
import com.scaler.productservice.service.InitService;
import com.scaler.productservice.service.ProductService;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InitService initService; // Mock InitService here as well

    @MockBean(name = "productService")
    private ProductService productService;

    // UT for controller method invoked via APIs
    @Test
    void getAllProductsReturnEmptyListWhenProductsAvailable() throws Exception {
        // arrange
        ProductListResponseDTO emptyProductListResponse = new ProductListResponseDTO();
        when(productService.getAllProducts()).thenReturn(emptyProductListResponse);

        mockMvc.perform(get("/products"))
                .andExpect(status().is(200))
                .andExpect(content().string("{\"products\":[]}"));
    }

    @Test
    void getAllProductsReturnProducts() throws Exception {
        // arrange
        ProductListResponseDTO productListResponseDTO = new ProductListResponseDTO();
        ProductResponseDTO product1 = new ProductResponseDTO();
        product1.setId(UUID.fromString("dda0233e-e70d-4ed8-bc68-cd249f314bb5"));
        product1.setTitle("Laptop");
        product1.setCategory("Electronics");
        product1.setDescription("best laptop");
        product1.setPrice(1000);
        product1.setImage("some url");

        //        ProductResponseDTO product2= new ProductResponseDTO();
        //        product2.setId(UUID.fromString("dda0233e-e70d-4ed8-bc68-cd249f314bb5"));
        //        product2.setTitle("FOn");
        //        product2.setCategory("Electronics");
        //        product2.setDescription("best FOn");
        //        product2.setPrice(2000);
        //        product2.setImage("some url");

        productListResponseDTO.setProducts(List.of(product1));

        when(productService.getAllProducts()).thenReturn(productListResponseDTO);

        mockMvc.perform(get("/products"))
                .andExpect(status().is(200))
                .andExpect(
                        content()
                                .string(
                                        "{\"products\":[{\"id\":\"dda0233e-e70d-4ed8-bc68-cd249f314bb5\",\"title\":\"Laptop\",\"price\":1000.0,\"category\":\"Electronics\",\"description\":\"best laptop\",\"image\":\"some url\"}]}"));
    }

    //  UNIT TESTING POST REQUEST
    @Test
    void createProductSuccess() throws Exception {
        ProductRequestDTO productRequestDTO = new ProductRequestDTO();
        productRequestDTO.setTitle("Laptop");
        productRequestDTO.setCategory("Electronics");
        productRequestDTO.setDescription("best laptop");
        productRequestDTO.setPrice(1000);
        productRequestDTO.setImage("some url");

        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        productResponseDTO.setId(UUID.fromString("dda0233e-e70d-4ed8-bc68-cd249f314bb5"));
        productResponseDTO.setTitle("Laptop");
        productResponseDTO.setCategory("Electronics");
        productResponseDTO.setDescription("best laptop");
        productResponseDTO.setPrice(1000);
        productResponseDTO.setImage("some url");

        String requestJson = convertToJson(productRequestDTO);
        String responseJson = convertToJson(productResponseDTO);

        when(productService.createProduct(eq(productRequestDTO))).thenReturn(productResponseDTO);

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().is(200))
                .andExpect(content().string(responseJson));
    }

    // UNIT TESTING  DELETE  BY ID
    @Test
    void deleteProductByIdSucess() throws Exception {

        when(productService.deleteProduct(5)).thenReturn(true);
        mockMvc.perform(delete("/products/5"))
                .andExpect(status().is(200))
                .andExpect(content().string("true"));
    }
    // UNIT TESTING  GET PRODUCT BY ID
    @Test
    void findProductByIdSucesss() throws Exception {

        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        productResponseDTO.setId(UUID.fromString("dda0233e-e70d-4ed8-bc68-cd249f314bb5"));
        productResponseDTO.setTitle("Laptop");
        productResponseDTO.setCategory("Electronics");
        productResponseDTO.setDescription("best laptop");
        productResponseDTO.setPrice(1000);
        productResponseDTO.setImage("some url");

        String resString = convertToJson(productResponseDTO);

        when(productService.getProductById(1)).thenReturn(productResponseDTO);
        mockMvc.perform(get("/products/1"))
                .andExpect(status().is(200))
                .andExpect(content().string(resString));
    }

    // UNIT TESTING  GET PRODUCT BY ID FAILURE
    @Test
    void findProductByIdFailure() throws Exception {
        when(productService.getProductById(1)).thenThrow(new ProductNotFoundException("Product Not Found"));
        mockMvc.perform(get("/products/1"))
                .andExpect(status().is(404))
                .andExpect(content().string("{\"message\":\"Product Not Found\",\"messageCode\":404}"));
    }

    //  UNIT TESTING GET PRODUCT BY TITLE
    @Test
    void findProductByTitleSucesss() throws Exception {

        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        productResponseDTO.setId(UUID.fromString("dda0233e-e70d-4ed8-bc68-cd249f314bb5"));
        productResponseDTO.setTitle("Laptop");
        productResponseDTO.setCategory("Electronics");
        productResponseDTO.setDescription("best laptop");
        productResponseDTO.setPrice(1000);
        productResponseDTO.setImage("some url");

        String resString = convertToJson(productResponseDTO);

        when(productService.findProductByTitle("Laptop")).thenReturn(productResponseDTO);
        mockMvc.perform(get("/products/title/Laptop"))
                .andExpect(status().is(200))
                .andExpect(content().string(resString));
    }

    private String convertToJson(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(object);
    }
}
