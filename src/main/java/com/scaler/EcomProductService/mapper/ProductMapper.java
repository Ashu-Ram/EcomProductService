package com.scaler.EcomProductService.mapper;

import com.scaler.EcomProductService.dto.FakeStoreProductRequestDTO;
import com.scaler.EcomProductService.dto.FakeStoreProductResponseDTO;
import com.scaler.EcomProductService.dto.ProductRequestDTO;
import com.scaler.EcomProductService.dto.ProductResponseDTO;

public class ProductMapper {

    public static FakeStoreProductRequestDTO productRequestToFakeStoreProductRequest(ProductRequestDTO productRequestDTO)
    {
FakeStoreProductRequestDTO fakeStoreProductRequestDTO= new FakeStoreProductRequestDTO();
fakeStoreProductRequestDTO.setTitle(productRequestDTO.getTitle());
fakeStoreProductRequestDTO.setPrice(productRequestDTO.getPrice());
fakeStoreProductRequestDTO.setCategory(productRequestDTO.getCategory());
fakeStoreProductRequestDTO.setDescription(productRequestDTO.getDescription());
fakeStoreProductRequestDTO.setImage(productRequestDTO.getImage());


return fakeStoreProductRequestDTO;

    }

    public static ProductResponseDTO fakeProductResponseToProductResponse(FakeStoreProductResponseDTO
                                                                          fakeStoreProductResponseDTO)
    {
      ProductResponseDTO productResponseDTO= new ProductResponseDTO();
      productResponseDTO.setId(fakeStoreProductResponseDTO.getId());
      productResponseDTO.setPrice(fakeStoreProductResponseDTO.getPrice());
      productResponseDTO.setImage(fakeStoreProductResponseDTO.getImage());
      productResponseDTO.setDescription(fakeStoreProductResponseDTO.getDescription());
      productResponseDTO.setCategory(fakeStoreProductResponseDTO.getCategory());;
      productResponseDTO.setTitle(fakeStoreProductResponseDTO.getTitle());

      return productResponseDTO;
    }

}
