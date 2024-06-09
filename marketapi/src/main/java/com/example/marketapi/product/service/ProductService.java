package com.example.marketapi.product.service;

import com.example.marketapi.product.dto.request.ProductRequestDto;
import com.example.marketapi.product.dto.response.ProductPreservedResponseDto;
import com.example.marketapi.product.dto.response.ProductPurchasedResponseDto;
import com.example.marketapi.product.dto.response.ProductResponseDto;

import java.util.List;

public interface ProductService {

    void addProduct(ProductRequestDto productRequestDto);

    void buyProduct(Long id);
    List<ProductResponseDto> getProductList();

    ProductResponseDto getProductDetails(Long id);
}
