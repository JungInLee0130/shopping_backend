package com.example.marketapi.service;

import com.example.marketapi.dto.product.request.ProductRequestDto;
import com.example.marketapi.dto.product.response.ProductResponseDto;

import java.util.List;

public interface ProductService {

    void addProduct(ProductRequestDto productRequestDto);

    void buyProduct(Long id);
    List<ProductResponseDto> getProductList();

    ProductResponseDto getProductDetails(Long id);
}
