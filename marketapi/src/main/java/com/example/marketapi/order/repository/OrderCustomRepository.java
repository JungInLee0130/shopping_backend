package com.example.marketapi.order.repository;

import com.example.marketapi.order.dto.response.OrderLogResponseDto;
import com.example.marketapi.order.dto.response.OrderResponseDto;

import java.util.List;
import java.util.Optional;

public interface OrderCustomRepository {
    Optional<List<OrderLogResponseDto>> findPreservedOrderLogAll(Long memberId);
    Optional<List<OrderLogResponseDto>> findPreservedOrderLogAllByJPQL(Long memberId);
    Optional<OrderResponseDto> findOrderDetail(Long orderId);
}
