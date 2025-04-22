package com.example.marketapi.order.dto.response;


import com.example.marketapi.order.domain.OrderStatus;
import com.example.marketapi.product.domain.Price;
import com.example.marketapi.product.domain.Quantity;

public record OrderLogResponseDto (OrderStatus orderLogStatus,
                                   String purchaserName,
                                   String sellerName,
                                   String productName,
                                   Price price,
                                   Quantity quantity){
}
