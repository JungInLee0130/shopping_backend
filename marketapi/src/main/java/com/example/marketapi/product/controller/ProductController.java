package com.example.marketapi.product.controller;

import com.example.marketapi.auth.annotation.RoleUser;
import com.example.marketapi.global.exception.CustomException;
import com.example.marketapi.global.exception.ErrorCode;
import com.example.marketapi.member.domain.MemberDetails;
import com.example.marketapi.member.domain.Role;
import com.example.marketapi.product.domain.Price;
import com.example.marketapi.product.domain.Quantity;
import com.example.marketapi.product.dto.request.ProductRequestDto;
import com.example.marketapi.product.dto.response.ProductResponseDto;
import com.example.marketapi.product.service.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    // 제품 등록 : 회원
    @RoleUser
    @PostMapping("/add")
    public ResponseEntity<Void> addProduct(
            @AuthenticationPrincipal MemberDetails memberDetails,
            @RequestBody @Valid ProductRequestDto request) {

        Price price = new Price(request.price());
        Quantity quantity = new Quantity(request.quantity());
        Long productId = productService.addProduct(request.name(), price, quantity, memberDetails.getMemberId());

        // 해당 상품 등록 url로 이동.
        return ResponseEntity.created(URI.create("/api/v1/product/add/" + productId)).build();
    }

    // 구매 : 회원
    @PostMapping("/buy")
    public ResponseEntity<Void> buyProduct(@AuthenticationPrincipal MemberDetails memberDetails,
                                           @RequestParam @Valid Long productId) {

        productService.buyProduct(productId, memberDetails.getMemberId());
        return ResponseEntity.status(HttpStatus.OK).build();

    }

    // 목록 조회 : 비회원 가능
    @GetMapping("/list")
    public ResponseEntity<List<ProductResponseDto>> getProductList() {
        return ResponseEntity.ok(productService.getProductList());
    }

    // 상세 정보 : 비회원 가능
    @GetMapping("/details")
    public ResponseEntity<ProductResponseDto> getProductDetails(@RequestParam Long id) {
        return ResponseEntity.ok(productService.getProductDetails(id));
    }
}
