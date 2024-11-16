package com.example.marketapi.product.service;

import com.example.marketapi.global.exception.CustomException;
import com.example.marketapi.global.exception.ErrorCode;
import com.example.marketapi.member.domain.Member;
import com.example.marketapi.member.repository.MemberRepository;
import com.example.marketapi.product.domain.Price;
import com.example.marketapi.product.domain.Quantity;
import com.example.marketapi.product.domain.Reservation;
import com.example.marketapi.product.entity.Product;
import com.example.marketapi.product.dto.request.ProductRequestDto;
import com.example.marketapi.product.dto.response.ProductResponseDto;
import com.example.marketapi.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductService {

    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    @Transactional
    public Long addProduct(String name, Price price, Quantity quantity, Long sellerId) {
        Member seller = memberRepository.getReferenceById(sellerId);

        Product product = new Product(seller,name,price,quantity);

        productRepository.save(product);

        return product.getId();
    }


    public void buyProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND, "등록한 제품이 없습니다."));

        product.updatePreserved(Reservation.RESERVED);
    }


    public List<ProductResponseDto> getProductList() {
        List<Product> products = productRepository.findAll();

        return ProductResponseDto.of(products);
    }


    public ProductResponseDto getProductDetails(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND, "해당 제품이 없습니다."));

        return ProductResponseDto.of(product);
    }
}
