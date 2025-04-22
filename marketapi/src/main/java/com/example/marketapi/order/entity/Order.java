package com.example.marketapi.order.entity;

import com.example.marketapi.member.entity.Member;
import com.example.marketapi.order.domain.OrderStatus;
import com.example.marketapi.product.entity.Product;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Table(name = "ORDERS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id; // 주문번호

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "member_id", name = "purchaser_id", nullable = false)
    private Member purchaser; // 구매자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product; // 제품

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status_code")
    private OrderStatus orderStatus;

    @Builder
    public Order(Member purchaser, Product product, OrderStatus orderStatus) {
        this.purchaser = purchaser;
        this.product = product;
        this.orderStatus = OrderStatus.PROGRESS; // 처음 생성시 진행중
    }
}
