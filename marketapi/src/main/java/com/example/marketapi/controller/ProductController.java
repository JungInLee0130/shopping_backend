package com.example.marketapi.controller;

import com.example.marketapi.domain.member.Role;
import com.example.marketapi.dto.product.request.ProductRequestDto;
import com.example.marketapi.dto.product.response.ProductResponseDto;
import com.example.marketapi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    // 제품 등록 : 회원
    @PostMapping("/add")
    public ResponseEntity<Void> addProduct(Role role, ProductRequestDto productDto) {
        if (role == Role.User) {
            productService.addProduct(productDto);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // GUEST일 경우
    }

    // 구매 : 회원
    @PutMapping("/buy")
    public ResponseEntity<Void> buyProduct(Role role, Long id) {
        if (role == Role.User) {
            productService.buyProduct(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    // 목록 조회 : 비회원 가능
    @GetMapping("/list")
    public ResponseEntity<List<ProductResponseDto>> getProductList() {
        return ResponseEntity.ok(productService.getProductList());
    }

    // 상세 정보 : 비회원 가능
    @GetMapping("/details")
    public ResponseEntity<ProductResponseDto> getProductDetails(Long id){
        return ResponseEntity.ok(productService.getProductDetails(id));
    }
}
