package com.example.marketapi.order.dto.response;

import com.example.marketapi.order.domain.Order;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderResponseDto {
    private String sellerName;
    private String purchaserName;

    public static OrderResponseDto of(Order order) {
        return OrderResponseDto.builder()
                .sellerName(order.getSellerName())
                .purchaserName(order.getPurchaserName())
                .build();
    }
}
