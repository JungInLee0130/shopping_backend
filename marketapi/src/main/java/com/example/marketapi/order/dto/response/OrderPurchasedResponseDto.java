package com.example.marketapi.order.dto.response;

import com.example.marketapi.product.entity.Product;
import lombok.Builder;

@Builder
public class OrderPurchasedResponseDto {
    // 제품
    private String name;
    private int price;


    public static OrderPurchasedResponseDto of(Product product) {
        return OrderPurchasedResponseDto.builder()
                .name(product.getName())
                .price(product.getPrice().value())
                .build();
    }
}
