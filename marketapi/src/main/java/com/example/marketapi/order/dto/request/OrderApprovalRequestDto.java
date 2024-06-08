package com.example.marketapi.order.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderApprovalRequestDto {
    private String productName;
    private String sellerName;
    private String purchaserName;
}
