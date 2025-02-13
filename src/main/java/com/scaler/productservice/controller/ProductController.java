package com.scaler.productservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scaler.productservice.client.UserServiceClient;
import com.scaler.productservice.dto.*;
import com.scaler.productservice.exception.InvalidTokenException;
import com.scaler.productservice.exception.ProductNotFoundException;
import com.scaler.productservice.service.ProductService;
import java.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    private final ProductService productService; // immutable
    private final UserServiceClient userServiceClient;

    @Autowired // Autowired for constructor injection is Optional from spring 4+ version
    public ProductController(
            @Qualifier("fakeStoreProductService") ProductService productService, UserServiceClient userServiceClient) {
        this.productService = productService;
        this.userServiceClient = userServiceClient;
    }

    /**
     * Field Injection
     * @Autowired
     * @Qualifier("fakeStoreProductService")
     * private ProductService productService;
     */
    @GetMapping("/products")
    public ResponseEntity getAllProducts(@RequestHeader("token") String token) throws Exception {

        validateUser(token);
        ProductListResponseDTO response = productService.getAllProducts();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity getProductFromId(@PathVariable("id") int id, @RequestHeader("token") String token)
            throws Exception {

        validateUser(token);
        ProductResponseDTO response = productService.getProductById(id);
        return ResponseEntity.ok(response);
    }
    //    @GetMapping("/products/{id}")
    //    public ResponseEntity getProductFromId(@PathVariable("id") UUID id, @RequestHeader("token") String token)
    //            throws Exception {
    //
    //        validateUser(token);
    //        ProductResponseDTO response = productService.getProductById(id);
    //        return ResponseEntity.ok(response);
    //    }

    @GetMapping("/products/title/{title}")
    public ResponseEntity getProductFromTitle(@PathVariable("title") String title, @RequestHeader("token") String token)
            throws ProductNotFoundException, ProductNotFoundException {
        /*
        ProductResponseDTO p1 =  new ProductResponseDTO();
        p1.setId(1);
        p1.setTitle("Iphone 15 pro");
        p1.setPrice(150000);
        p1.setImage("www.google.com/images/iphone");
        p1.setDescription("Kafi Mehnga phone");
        p1.setCategory("Electronics");

        ProductResponseDTO p2 =  new ProductResponseDTO();
        p2.setId(2);
        p2.setTitle("Macbook Pro");
        p2.setPrice(250000);
        p2.setImage("www.google.com/images/macbook");
        p2.setDescription("Kafi Mehnga laptop");
        p2.setCategory("Electronics");

        List<ProductResponseDTO> products = Arrays.asList(p1, p2);
        return ResponseEntity.ok(products);
        */
        ProductResponseDTO response = productService.findProductByTitle(title);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/products")
    public ResponseEntity createProduct(
            @RequestBody ProductRequestDTO productRequestDTO, @RequestHeader("token") String token) {
        ProductResponseDTO responseDTO = productService.createProduct(productRequestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity deleteProductById(@PathVariable("id") int id, @RequestHeader("token") String token) {
        boolean response = productService.deleteProduct(id);
        return ResponseEntity.ok(response);
    }

    private void validateUser(String token) throws Exception {
        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(chunks[1]));
        ObjectMapper mapper = new ObjectMapper();
        JwtPayloadDTO jwtPayload = mapper.readValue(payload, JwtPayloadDTO.class);
        int userId = jwtPayload.getUserId();
        ValidateTokenDTO validateTokenDTO = new ValidateTokenDTO(userId, token);
        String result = userServiceClient.validateToken(validateTokenDTO);
        if (!result.contains(SessionStatus.ACTIVE.name())) {
            throw new InvalidTokenException("Token is not valid");
        }
    }
    //    @GetMapping("/products")
    //    public ResponseEntity getAllProducts() {
    //
    //        ProductListResponseDTO response = productService.getAllProducts();
    //        return ResponseEntity.ok(response);
    //    }
    //
    //    @GetMapping("/products/{id}")
    //    public ResponseEntity getProductById(@PathVariable("id") int id) throws ProductNotFoundException {
    //        ProductResponseDTO response = productService.getProductById(id);
    //        return ResponseEntity.ok(response);
    //    }
    //
    //    @GetMapping("/products/title/{title}")
    //    public ResponseEntity getProductByTitle(@PathVariable("title") String title) throws ProductNotFoundException {
    //        ProductResponseDTO response = productService.findProductByTitle(title);
    //        return ResponseEntity.ok(response);
    //    }
    //
    //    @PostMapping("/products")
    //    public ResponseEntity createProduct(@RequestBody ProductRequestDTO productRequestDTO) {
    //        ProductResponseDTO responseDTO = productService.createProduct(productRequestDTO);
    //        return ResponseEntity.ok(responseDTO);
    //    }
    //
    //    @DeleteMapping("/products/{id}")
    //    public ResponseEntity deleteProductById(@PathVariable("id") int id) {
    //        boolean response = productService.deleteProduct(id);
    //        return ResponseEntity.ok(response);
    //    }
}
