package com.example.marketapi.order.dto.request;

import lombok.Getter;

@Getter
public class OrderRequestDto {
    private String sellerName; // 판매자
    private String purchaserName; // 구매자
    private Long productId; // 제품번호
}
