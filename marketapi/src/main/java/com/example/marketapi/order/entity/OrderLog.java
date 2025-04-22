package com.example.marketapi.order.entity;

import com.example.marketapi.order.domain.OrderStatus;
import com.example.marketapi.util.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ORDERLOGS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderLog extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_log_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "order_id", name = "order_id", nullable = false)
    private Order order;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_log_status_code")
    private OrderStatus orderLogStatus;

    @Builder
    public OrderLog(Order order, OrderStatus orderLogStatus) {
        this.order = order;
        this.orderLogStatus = orderLogStatus;
    }
}
