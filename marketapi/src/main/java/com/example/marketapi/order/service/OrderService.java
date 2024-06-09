package com.example.marketapi.order.service;

import com.example.marketapi.order.dto.request.OrderRequestDto;
import com.example.marketapi.order.dto.response.OrderPreservedResponseDto;
import com.example.marketapi.order.dto.response.OrderPurchasedResponseDto;
import com.example.marketapi.order.dto.response.OrderResponseDto;
import com.example.marketapi.order.dto.request.OrderApprovalRequestDto;

import java.util.List;

public interface OrderService {
    void orderProduct(OrderRequestDto orderRequestDto);

    OrderResponseDto orderDetails(String name);

    List<OrderPurchasedResponseDto> getPurchasedProducts(String purchaserName);

    List<OrderPreservedResponseDto> getPreservedProducts(String name);

    void approveSell(Long id);
}
