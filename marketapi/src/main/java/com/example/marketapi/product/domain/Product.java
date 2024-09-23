package com.example.marketapi.product.domain;

import com.example.marketapi.order.domain.Order;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "PRODUCTS")
public class Product {
    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name")
    private String name; // 제품명

    @Column(name = "product_price")
    private String price; // 가격

    @Column(name = "product_preserved")
    @Enumerated(EnumType.STRING)
    private Preserved preserved; // 예약상태

    @Column(name = "seller_name")
    private String sellerName; // 판매자 이름

    @OneToOne(mappedBy = "product")
    private Order order;

    @Builder
    public Product(String name, String price, Preserved preserved) {
        this.name = name;
        this.price = price;
        this.preserved = preserved;
    }

    public void updatePreserved(Preserved preserved) {
        this.preserved = preserved;
    }
}
