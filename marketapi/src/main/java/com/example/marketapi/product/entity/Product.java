package com.example.marketapi.product.entity;

import com.example.marketapi.global.exception.CustomException;
import com.example.marketapi.global.exception.ErrorCode;
import com.example.marketapi.member.entity.Member;
import com.example.marketapi.order.domain.Order;
import com.example.marketapi.product.domain.*;
import com.example.marketapi.util.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "PRODUCTS")
public class Product extends BaseTimeEntity {
    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id", referencedColumnName = "member_id")
    private Member seller; // 판매자 정보

    @Column(name = "name")
    private String name; // 제품명

    @Column(name = "price")
    @Convert(converter = PriceConverter.class)
    private Price price; // 가격

    @Column(name = "quantity")
    @Convert(converter = QuantityConverter.class)
    private Quantity quantity;

    @Column(name = "reservation_code")
    @Enumerated(EnumType.STRING)
    private Reservation reservation; // 예약상태

    @OneToOne(mappedBy = "product")
    private Order order; // 이게 transact로 바뀐듯. 나중에 해볼때 order로 바꿔보자.


    @Builder
    public Product(Member seller, String name, Price price, Quantity quantity) {
        validInitQuantity(quantity); // product 생성시 validation 체크
        this.seller = seller;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.reservation = Reservation.SALE;
    }

    public Product(Long id, Member seller, String name, Price price, Quantity quantity) {
        this.id = id;
        this.seller = seller;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    private void validInitQuantity(Quantity quantity) {
        if (quantity.value() <= 0) {
            throw new CustomException(ErrorCode.BAD_REQUEST, "수량이 0이하일수없습니다.");
        }
    }

    public void setId(Long id){
        this.id = id;
    }

    public void purchase(){ // 도메인값 변경하는거니까 여기다 둔듯?
        this.quantity = Quantity.minus(this.quantity);

        if (quantity.value() == 0 && this.reservation == Reservation.SALE) {
            this.reservation = Reservation.RESERVED; // quantity가 0이되면 예약.. 흠...맞나?
        }
    }

    public void complete(){
        if (this.reservation == Reservation.RESERVED) {
            this.reservation = Reservation.FINISH;
        }
    }

    public void updatePrice(Price price) {
        this.price = price;
    }

    public void updatePreserved(Reservation reservation) {
        this.reservation = reservation;
    }
}
