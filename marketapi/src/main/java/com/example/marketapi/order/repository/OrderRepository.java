package com.example.marketapi.order.repository;

import com.example.marketapi.order.domain.Order;
import com.example.marketapi.product.domain.Preserved;
import com.example.marketapi.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, OrderCustomRepository {
    Optional<Order> findByProductId(Long productId);
    Optional<Order> findBySellerNameOrPurchaserName(String name);
    Optional<List<Order>> findAllByPurchaserName(String purchaserName);
}
