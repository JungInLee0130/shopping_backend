package com.example.marketapi.payment.response;

import com.example.marketapi.payment.domain.PaymentState;
import com.example.marketapi.payment.entity.Payment;

public record PaymentProgressResponse (Long merchant_uid,
                                       Long amount,
                                       PaymentState paymentState){
    public PaymentProgressResponse (Payment payment){
        this(payment.getMerchant_uid(), payment.getAmount(), payment.getPaymentState());
    }
}
