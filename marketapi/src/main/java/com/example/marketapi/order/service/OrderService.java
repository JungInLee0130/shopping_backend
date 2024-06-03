package com.example.marketapi.order.service;

import com.example.marketapi.order.dto.request.OrderRequestDto;
import com.example.marketapi.order.dto.response.OrderResponseDto;

public interface OrderService {
    void orderProduct(OrderRequestDto orderRequestDto);

    OrderResponseDto orderDetails(String name);
}
