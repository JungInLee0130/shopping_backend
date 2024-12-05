package com.example.marketapi.payment.request;


import jakarta.validation.constraints.NotNull;

public record PaymentProgressRequest (@NotNull Long merchant_uid,
                                      @NotNull Long amount){
}
