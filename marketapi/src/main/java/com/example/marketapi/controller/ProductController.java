package com.example.marketapi.controller;

import com.example.marketapi.dto.product.request.ProductRequestDto;
import com.example.marketapi.dto.product.respose.ProductResponseDto;
import com.example.marketapi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/add")
    public void addProduct(ProductRequestDto productDto) {
        productService.addProduct(productDto);
    }

    // 목록 조회
    @GetMapping("/list")
    public List<ProductResponseDto> getProductList() {
        return productService.getProductList();
    }

    @GetMapping("/details")
    public ProductResponseDto getProductDetails(Long id){
        return productService.getProductDetails(id);
    }
}
