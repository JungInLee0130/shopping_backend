package com.example.marketapi.order.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public record OrderRequestDto (@NotNull Long purchaserId,
                               @NotNull Long productId){
}
