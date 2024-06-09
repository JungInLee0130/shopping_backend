package com.example.marketapi.order.dto.response;

import com.example.marketapi.product.domain.Product;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@Builder
public class OrderPurchasedResponseDto {
    // 제품
    private String productName;
    private String price;
    
    // 판매자 이름
    private String sellerName;

    public static OrderPurchasedResponseDto of(Product product) {
        return OrderPurchasedResponseDto.builder()
                .productName(product.getName())
                .price(product.getPrice())

                .sellerName(product.getSellerName())
                .build();
    }
}
