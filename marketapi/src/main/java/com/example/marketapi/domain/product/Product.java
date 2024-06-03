package com.example.marketapi.domain.product;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "PRODUCT")
public class Product {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public void updatePreserved(Preserved preserved) {
        this.preserved = preserved;
    }
}
