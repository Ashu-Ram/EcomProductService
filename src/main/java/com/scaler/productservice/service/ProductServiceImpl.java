package com.scaler.productservice.service;

import com.scaler.productservice.dto.ProductListResponseDTO;
import com.scaler.productservice.dto.ProductRequestDTO;
import com.scaler.productservice.dto.ProductResponseDTO;
import com.scaler.productservice.mapper.ProductMapper;
import com.scaler.productservice.model.Product;
import com.scaler.productservice.repo.PriceRepository;
import com.scaler.productservice.repo.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("productService")
public class ProductServiceImpl implements  ProductService {


    private  final PriceRepository priceRepository;
    private final ProductRepository productRepository;

    public ProductServiceImpl(PriceRepository priceRepository,
                              ProductRepository productRepository) {
        this.priceRepository = priceRepository;
        this.productRepository = productRepository;
    }

    @Override
    public ProductListResponseDTO getAllProducts() {

        List<Product> products = productRepository.findAll();

        ProductListResponseDTO productListResponseDTO = ProductMapper.convertProductListToProductListResponseDTO(products);

        return productListResponseDTO;
    }

    @Override
    public ProductResponseDTO getProductById(int id) {
        return null;
    }

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {
        return null;
    }



    @Override
    public boolean deleteProduct(int id) {
        return false;
    }

    @Override
    public Product updateProduct(int id, Product updatedProduct) {
        return null;
    }

    @Override
    public ProductResponseDTO findProductByTitle(String title) {
        // findAll() -> List of products
        // findbyid ()-> product by product id
Product product = productRepository.findByTitle(title);
ProductResponseDTO responseDTO= ProductMapper.convertProductToProductResponseDTO(product);
return responseDTO;


    }
}
