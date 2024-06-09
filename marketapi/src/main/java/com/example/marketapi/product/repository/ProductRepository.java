package com.example.marketapi.product.repository;

import com.example.marketapi.product.domain.Preserved;
import com.example.marketapi.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
