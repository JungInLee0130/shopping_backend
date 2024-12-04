package com.example.marketapi.transact.request;

import jakarta.validation.constraints.NotNull;

public record ConfirmRequest(@NotNull Long productId) {
}
