package com.example.marketapi.transact.repository;

import com.example.marketapi.transact.entity.Transact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransactRepository extends JpaRepository<Transact, Long>, TransactRepositoryCustom{

    List<Transact> findByBuyerId(Long buyerId);

    List<Transact> findByProductId(Long productId);
    Optional<Transact> findByBuyerIdAndProductId(Long buyerId, Long productId);
}
