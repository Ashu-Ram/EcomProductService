package com.scaler.productservice.controller;

import com.scaler.productservice.dto.GenericProductDto;
import com.scaler.productservice.dto.SearchRequestDto;
import com.scaler.productservice.service.SearchService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
public class SearchController {
    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    //    @PostMapping
    //    public Page<GenericProductDto> searchProducts(@RequestBody SearchRequestDto requestDto) {
    //
    //        List<GenericProductDto> genericProductDtos = searchService.searchProducts(requestDto.getTitle(),
    // requestDto.getPageNumber(), requestDto.getPageSize(),requestDto.getSortParams());
    //
    //        Page<GenericProductDto> genericProductDtoPage= new PageImpl<>(
    //
    //                genericProductDtos
    //        );
    //
    //        return genericProductDtoPage;
    //
    //    }

    @PostMapping
    public Page<GenericProductDto> searchProducts(@RequestBody SearchRequestDto requestDto) {

        // Fetch paginated product results
        Page<GenericProductDto> genericProductDtoPage = (Page<GenericProductDto>) searchService.searchProducts(
                requestDto.getTitle(),
                requestDto.getPageNumber(),
                requestDto.getPageSize(),
                requestDto.getSortParams());

        return genericProductDtoPage;
    }
}
