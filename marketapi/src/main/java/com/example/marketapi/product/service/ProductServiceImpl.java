package com.example.marketapi.product.service;

import com.example.marketapi.global.exception.CustomException;
import com.example.marketapi.global.exception.ErrorCode;
import com.example.marketapi.product.domain.Preserved;
import com.example.marketapi.product.domain.Product;
import com.example.marketapi.product.dto.request.ProductRequestDto;
import com.example.marketapi.order.dto.response.OrderPreservedResponseDto;
import com.example.marketapi.order.dto.response.OrderPurchasedResponseDto;
import com.example.marketapi.product.dto.response.ProductResponseDto;
import com.example.marketapi.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    @Override
    public void addProduct(ProductRequestDto productRequestDto) {
        Product product = productRequestDto.toEntity();

        productRepository.save(product);
    }

    @Override
    public void buyProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND, "등록한 제품이 없습니다."));

        product.updatePreserved(Preserved.PRESERVED);
    }

    @Override
    public List<ProductResponseDto> getProductList() {
        List<Product> products = productRepository.findAll();

        return ProductResponseDto.of(products);
    }

    @Override
    public ProductResponseDto getProductDetails(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND, "해당 제품이 없습니다."));

        return ProductResponseDto.of(product);
    }
}
