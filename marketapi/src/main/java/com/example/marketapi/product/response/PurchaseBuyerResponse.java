package com.example.marketapi.product.response;

import com.example.marketapi.transact.model.TransactState;

import java.util.List;

public record PurchaseBuyerResponse(Long buyerId,
                                    List<TransactState> transactStates) implements BuyInfo{
}
