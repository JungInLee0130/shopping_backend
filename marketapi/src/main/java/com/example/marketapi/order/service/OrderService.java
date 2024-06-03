package com.example.marketapi.order.service;

import com.example.marketapi.order.dto.OrderRequestDto;

public interface OrderService {
    void orderProduct(OrderRequestDto orderRequestDto);
}
