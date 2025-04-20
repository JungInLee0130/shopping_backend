package com.example.marketapi.order.repository;

import com.example.marketapi.order.dto.response.OrderResponseDto;
import com.querydsl.core.Tuple;
import org.hibernate.query.Order;

import java.util.List;
import java.util.Optional;

public interface OrderCustomRepository {
    //List<Tuple> findPreservedAllByName(String name);
    Optional<OrderResponseDto> findOrderDetail(Long orderId);
}
