package com.example.marketapi.payment.request;

import jakarta.validation.constraints.NotNull;

public record PaymentCancelRequest(@NotNull Long merchant_uid,
                                   @NotNull Long payAmount) {
}
