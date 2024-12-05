package com.example.marketapi.payment.response;

import com.example.marketapi.payment.domain.PaymentState;
import com.example.marketapi.payment.entity.Payment;

public record PaymentCancelResponse (Long merchant_uid,
                                     Long payAmount,
                                     Long actualAmount,
                                     PaymentState paymentState){
    public PaymentCancelResponse (Payment payment, Long actualAmount){
        this(payment.getMerchant_uid(), payment.getAmount(), actualAmount, payment.getPaymentState());
    }
}
