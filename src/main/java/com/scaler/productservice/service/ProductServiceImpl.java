package com.scaler.productservice.service;

import com.scaler.productservice.dto.ProductListResponseDTO;
import com.scaler.productservice.dto.ProductRequestDTO;
import com.scaler.productservice.dto.ProductResponseDTO;
import com.scaler.productservice.exception.InvalidTitleException;
import com.scaler.productservice.exception.ProductNotFoundException;
import com.scaler.productservice.mapper.ProductMapper;
import com.scaler.productservice.model.Product;
import com.scaler.productservice.repo.PriceRepository;
import com.scaler.productservice.repo.ProductRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service("productService")
public class ProductServiceImpl implements ProductService {
    // hello pom

    private final PriceRepository priceRepository;
    private final ProductRepository productRepository;
    //  private final OpenSearchProductRepository openSearchProductRepository;

    public ProductServiceImpl(PriceRepository priceRepository, ProductRepository productRepository) {
        this.priceRepository = priceRepository;
        this.productRepository = productRepository;
        //  this.openSearchProductRepository = openSearchProductRepository;
    }

    @Override
    public ProductListResponseDTO getAllProducts() {

        List<Product> products = productRepository.findAll();

        ProductListResponseDTO productListResponseDTO =
                ProductMapper.convertProductListToProductListResponseDTO(products);

        return productListResponseDTO;
    }

    @Override
    public ProductResponseDTO getProductById(int id) throws ProductNotFoundException {
        return null;
    }

    public ProductResponseDTO getProductById(UUID id) throws ProductNotFoundException {
        Optional<Product> productOptional = productRepository.findById(id);

        if (productOptional.isEmpty()) {
            throw new ProductNotFoundException("Product with ID " + id + " not found");
        }

        return ProductMapper.convertProductToProductResponseDTO(productOptional.get());
    }

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {
        return null;
    }

    //    public ProductResponseDTO createProduct(GenericProductDto genericProductDto) {
    //              //  Product product = new Product();
    //       // product.setTitle(genericProductDto.getTitle());
    //      //  product.setImage(genericProductDto.getImage());
    //       // product.setInventoryCount(10);
    //
    //        Product savedProduct  = productRepository.save(product);
    //       // openSearchProductRepository.save(savedProduct);
    //        return null;
    //    }

    @Override
    public boolean deleteProduct(int id) {
        return false;
    }

    @Override
    public Product updateProduct(int id, Product updatedProduct) {
        return null;
    }

    @Override
    public ProductResponseDTO findProductByTitle(String title) throws ProductNotFoundException {
        // findAll() -> List of products
        // findbyid ()-> product by product id

        if (title == null || title.isEmpty()) {
            throw new InvalidTitleException("Title is Invalid");
        }
        Product product = productRepository.findByTitle(title);
        if (product == null) {
            throw new ProductNotFoundException("product with given title is not available");
        }

        return ProductMapper.convertProductToProductResponseDTO(product);
    }
}
