package com.example.marketapi.domain.product;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name; // 제품명

    @Column(name = "price")
    private String price; // 가격

    @Column(name = "preserved")
    private Preserved preserved; // 예약상태

    @Builder
    private Product(String name, String price, Preserved preserved) {
        this.name = name;
        this.price = price;
        this.preserved = preserved;
    }
}
