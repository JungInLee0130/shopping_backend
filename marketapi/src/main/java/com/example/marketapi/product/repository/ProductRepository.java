package com.example.marketapi.product.repository;

import com.example.marketapi.product.entity.Product;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> , ProductRepositoryCustom{
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Product> findProductWithUpdateLockById(Long productId);
}
