package com.example.marketapi.order.service;

import com.example.marketapi.order.dto.request.OrderRequestDto;
import com.example.marketapi.order.dto.response.OrderResponseDto;
import com.example.marketapi.order.dto.request.OrderApprovalRequestDto;

public interface OrderService {
    void orderProduct(OrderRequestDto orderRequestDto);

    OrderResponseDto orderDetails(String name);

    void approveSell(OrderApprovalRequestDto orderApprovalRequestDto);
}
