package com.scaler.productservice.service;

import com.scaler.productservice.dto.GenericProductDto;
import com.scaler.productservice.model.Product;
import com.scaler.productservice.model.SortParam;
import com.scaler.productservice.repo.ProductRepository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class SearchService {

    private final ProductRepository productRepository;

    public SearchService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Ealstic Search Repository
    //    private final  OpenSearchProductRepository openSearchProductRepository;
    //
    //    public SearchService(OpenSearchProductRepository openSearchProductRepository) {
    //        this.openSearchProductRepository = openSearchProductRepository;
    //    }

    //    public List<GenericProductDto> searchProducts(String  query, int pageNumber , int pageSize, List<SortParam>
    // sortParams){
    //
    ////        Sort sort= Sort.by("title").ascending()
    ////                .and(Sort.by("price").ascending())
    ////                .and(Sort.by("rating").ascending());
    //
    //
    //        Sort sort=null;
    //        if(sortParams.get(0).getSortType().equals("ASC")){
    //            sort=Sort.by(sortParams.get(0).getSortParamName()).ascending();
    //        }
    //        else{
    //            sort= Sort.by(sortParams.get(0).getSortParamName()).descending();
    //        }
    //
    //        for (int i = 1; i < sortParams.size(); i++) {
    //            if (sortParams.get(i).getSortType().equals("ASC")) {
    //                sort.and(Sort.by(sortParams.get(i).getSortParamName()).ascending());
    //            } else {
    //                sort.and(Sort.by(sortParams.get(i).getSortParamName()).descending());
    //            }
    //        }
    //
    //
    //        PageRequest pageRequest= PageRequest.of(pageNumber,pageSize,sort);
    //        List<Product> products =productRepository.findAllByTitleContainingIgnoreCase(query,pageRequest);
    //       List<GenericProductDto> genericProductDtos= new ArrayList<>();
    //
    //       for(Product product:products) {
    //           genericProductDtos.add(GenericProductDto.from(product));
    //       }
    //
    //        return genericProductDtos;
    //    }

    public Page<GenericProductDto> searchProducts(
            String query, int pageNumber, int pageSize, List<SortParam> sortParams) {

        // Correctly build the Sort object
        Sort sort = Sort.unsorted();

        if (sortParams != null && !sortParams.isEmpty()) {
            sort = sortParams.stream()
                    .map(param -> "ASC".equalsIgnoreCase(param.getSortType())
                            ? Sort.by(param.getSortParamName()).ascending()
                            : Sort.by(param.getSortParamName()).descending())
                    .reduce(Sort::and)
                    .orElse(Sort.unsorted());
        }
        System.out.println("sorttt" + sort);

        // Create pageable object with sorting
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);

        // Fetch paginated data
        Page<Product> productPage = productRepository.findAllByTitleContainingIgnoreCase(query, pageRequest);

        // Page<Product> productPage = openSearchProductRepository.findAllByTitleContainingIgnoreCase(query,
        // pageRequest);

        // Convert Product -> GenericProductDto
        return productPage.map(GenericProductDto::from);
    }
}
