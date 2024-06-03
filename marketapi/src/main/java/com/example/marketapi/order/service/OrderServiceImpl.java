package com.example.marketapi.order.service;

import com.example.marketapi.order.domain.Order;
import com.example.marketapi.order.dto.request.OrderRequestDto;
import com.example.marketapi.order.dto.response.OrderResponseDto;
import com.example.marketapi.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;


    @Override
    public void orderProduct(OrderRequestDto orderRequestDto) {
        Order order = Order.builder()
                .productId(orderRequestDto.getProductId())
                .purchaserName(orderRequestDto.getPurchaserName())
                .sellerName(orderRequestDto.getSellerName())
                .build();

        orderRepository.save(order);
    }

    @Override
    public OrderResponseDto orderDetails(String name) {
        Order order = orderRepository.findBySellerNameOrPurchaserName(name)
                .orElseThrow(() -> new NoSuchElementException());
        return OrderResponseDto.of(order);
    }
}
