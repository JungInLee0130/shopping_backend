package com.example.marketapi.order.repository;

import com.example.marketapi.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, OrderCustomRepository {
    Optional<Order> findByProductId(Long productId);
    Optional<List<Order>> findAllByPurchaserName(String purchaserName);
}
