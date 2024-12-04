package com.example.marketapi.transact.entity;

import com.example.marketapi.member.entity.Member;
import com.example.marketapi.product.domain.Price;
import com.example.marketapi.product.domain.PriceConverter;
import com.example.marketapi.product.entity.Product;
import com.example.marketapi.util.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "TRANSACTS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Transact extends BaseTimeEntity {
    @Id
    @Column(name = "transact_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer_id")
    Member buyer; // 구매자 아이디

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    Product product; // 제품 아이디

    @Convert(converter = PriceConverter.class)
    private Price price;

    public Transact(Member buyer, Product product) {
        this.buyer = buyer;
        this.product = product;
        this.price = product.getPrice();
    }
}
