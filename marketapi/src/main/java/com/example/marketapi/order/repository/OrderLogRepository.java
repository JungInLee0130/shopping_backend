package com.example.marketapi.order.repository;

import com.example.marketapi.order.entity.Order;
import com.example.marketapi.order.entity.OrderLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderLogRepository extends JpaRepository<OrderLog, Long>, OrderLogCustomRepository{
}
