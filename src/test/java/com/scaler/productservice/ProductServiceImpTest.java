package com.scaler.productservice;

import static org.mockito.Mockito.when;

import com.scaler.productservice.dto.ProductResponseDTO;
import com.scaler.productservice.exception.InvalidTitleException;
import com.scaler.productservice.exception.ProductNotFoundException;
import com.scaler.productservice.model.Category;
import com.scaler.productservice.model.Price;
import com.scaler.productservice.model.Product;
import com.scaler.productservice.repo.ProductRepository;
import com.scaler.productservice.service.ProductServiceImpl;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class ProductServiceImpTest {

    @Mock // we need a mock object of the given attribute
    private ProductRepository productRepository;

    @InjectMocks // this is the class we want to test, and this is where we would inject the mock objects
    private ProductServiceImpl productService;

    @BeforeEach
    public void setup() {
        System.out.println("Hello World from BeforeEach!!!!!!!!");
        MockitoAnnotations.openMocks(this); // creates auto closeable resources for each test method
    }

    @Test
    void testFindProductByTitleSuccess() throws ProductNotFoundException {
        // Arrange
        Price mockPrice = new Price();
        mockPrice.setAmount(100);
        Category mockCategory = new Category();
        mockCategory.setCategoryName("mockCategory");
        String testTitle = "testTitle";
        Product mockProduct = new Product();
        mockProduct.setId(UUID.randomUUID());
        mockProduct.setTitle(testTitle);
        mockProduct.setDescription("testDescription");
        mockProduct.setPrice(mockPrice);
        mockProduct.setCategory(mockCategory);
        when(productRepository.findByTitle(testTitle)).thenReturn(mockProduct);

        // Act
        ProductResponseDTO acutalResponse = productService.findProductByTitle(testTitle);

        // Assert
        Assertions.assertEquals(acutalResponse.getTitle(), mockProduct.getTitle());
        Assertions.assertEquals(acutalResponse.getDescription(), mockProduct.getDescription());
        Assertions.assertEquals(acutalResponse.getId(), mockProduct.getId());
        Assertions.assertEquals(
                acutalResponse.getPrice(), mockProduct.getPrice().getAmount());
    }

    @Test
    void testFindByProductByTitleRepo_ResponseWithNullObject() {
        String testTitle = "testProductTitle";
        when(productRepository.findByTitle(testTitle)).thenReturn(null);

        Assertions.assertThrows(ProductNotFoundException.class, () -> productService.findProductByTitle(testTitle));
    }

    @Test
    void testFindProductByTitle_NullTitle() {
        // Arrange
        Price mockPrice = new Price();
        mockPrice.setAmount(100);
        Category mockCategory = new Category();
        mockCategory.setCategoryName("mockCategory");
        String testTitle = null;
        Product mockProduct = new Product();
        mockProduct.setId(UUID.randomUUID());
        mockProduct.setTitle(testTitle);
        mockProduct.setDescription("testDescription");
        mockProduct.setPrice(mockPrice);
        mockProduct.setCategory(mockCategory);
        when(productRepository.findByTitle(testTitle)).thenReturn(mockProduct);

        // Assert & Act

        Assertions.assertThrows(InvalidTitleException.class, () -> productService.findProductByTitle(testTitle));
    }

    @Test
    void testFindProductByTitle_Empty_Title() {
        // Arrange
        Price mockPrice = new Price();
        mockPrice.setAmount(100);
        Category mockCategory = new Category();
        mockCategory.setCategoryName("mockCategory");
        String testTitle = "";
        Product mockProduct = new Product();
        mockProduct.setId(UUID.randomUUID());
        mockProduct.setTitle(testTitle);
        mockProduct.setDescription("testDescription");
        mockProduct.setPrice(mockPrice);
        mockProduct.setCategory(mockCategory);
        when(productRepository.findByTitle(testTitle)).thenReturn(mockProduct);

        // Assert & Act

        Assertions.assertThrows(InvalidTitleException.class, () -> productService.findProductByTitle(testTitle));
    }
}
