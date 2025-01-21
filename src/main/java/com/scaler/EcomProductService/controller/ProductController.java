package com.scaler.EcomProductService.controller;


import com.scaler.EcomProductService.dto.ProductRequestDTO;
import com.scaler.EcomProductService.dto.ProductResponseDTO;
import com.scaler.EcomProductService.dto.ProductListResponseDTO;
import com.scaler.EcomProductService.exception.ProductNotFoundException;
import com.scaler.EcomProductService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class ProductController {



    private final ProductService productService;// immutable

    @Autowired // Autowired for constructor injection is Optional from spring 4+ version
    public ProductController(@Qualifier("fakeStoreProductService") ProductService productService) {
        this.productService = productService;
    }


    /**
    Field Injection
    @Autowired
    @Qualifier("fakeStoreProductService")
    private ProductService productService;
    */

   @GetMapping("/products")
    public ResponseEntity getAllProducts(){

        ProductListResponseDTO response= productService.getAllProducts();

return ResponseEntity.ok(response);


//       ProductResponseDTO p1= new ProductResponseDTO();
//          p1.setId(1);
//          p1.setTitle("Iphone 15 pro");
//          p1.setPrice(150000);
//          p1.setImage("www.google.com/images/iphone");
//          p1.setDescription("Kafi mEhnga Phone");
//          p1.setCategory("Electronics");
//
//        ProductResponseDTO p2= new ProductResponseDTO();
//        p2.setId(2);
//        p2.setTitle("Macbook pro");
//        p2.setPrice(250000);
//        p2.setImage("www.google.com/images/macbook");
//        p2.setDescription("Kafi mEhnga Phone");
//        p2.setCategory("Electronics");
//
//
//        List<ProductResponseDTO> products= Arrays.asList(p1,p2);
//
//        return ResponseEntity.ok(products);

    }


    @GetMapping("/products/{id}")
    public ResponseEntity getProductById(@PathVariable("id") int id) throws ProductNotFoundException {

        ProductResponseDTO response = productService.getProductById(id);

        return ResponseEntity.ok(response);

    }

    @PostMapping("/products")
    public ResponseEntity createProduct(@RequestBody ProductRequestDTO productRequestDTO)
    {
   ProductResponseDTO responseDTO= productService.createProduct(productRequestDTO);

   return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/products/{id}")
public ResponseEntity deleteProductById(@PathVariable("id")int id)
{
    boolean response = productService.deleteProduct(id);

    return ResponseEntity.ok(response);
}



    }
