package com.example.marketapi.product.response;

import com.example.marketapi.transact.entity.Transact;
import com.example.marketapi.transact.model.TransactState;

public record PurchaseDetailResponse(TransactState transactState) implements BuyInfo {
}
