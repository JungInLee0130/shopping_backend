package com.example.marketapi.order.repository;

import com.example.marketapi.order.dto.response.OrderLogResponseDto;
import com.example.marketapi.order.entity.OrderLog;

import java.util.List;
import java.util.Optional;

public interface OrderLogCustomRepository {
    Optional<List<OrderLogResponseDto>> findAllByPurchaser(Long memberId);
}
