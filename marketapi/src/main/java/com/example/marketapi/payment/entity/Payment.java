package com.example.marketapi.payment.entity;

import com.example.marketapi.payment.domain.PaymentState;
import com.example.marketapi.transact.entity.Transact;
import com.example.marketapi.transact.entity.TransactLog;
import com.example.marketapi.util.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PAYMENTS")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "merchant_uid")
    Long merchant_uid;

    @Column(name = "payment_amount")
    Long amount;

    @Column(name = "payment_state_code")
    @Enumerated(EnumType.STRING)
    PaymentState paymentState;

    public Payment(Long merchant_uid, Long amount, PaymentState paymentState) {
        this.merchant_uid = merchant_uid;
        this.amount = amount;
        this.paymentState = paymentState;
    }

    public void updatePaymentState(PaymentState paymentState) {
        this.paymentState = paymentState;
    }
}
