package com.example.marketapi.service;

import com.example.marketapi.domain.product.Preserved;
import com.example.marketapi.domain.product.Product;
import com.example.marketapi.dto.product.request.ProductRequestDto;
import com.example.marketapi.dto.product.respose.ProductResponseDto;
import com.example.marketapi.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    @Override
    public void addProduct(ProductRequestDto productRequestDto) {
        Product product = productRequestDto.toEntity();
        // 제품 넣기
        productRepository.save(product);
    }

    @Override
    public void buyProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException());

        // 예약중으로 변경
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
                .orElseThrow(() -> new NoSuchElementException());

        return ProductResponseDto.of(product);
    }
}
