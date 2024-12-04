package com.example.marketapi.product.response;

import com.example.marketapi.product.domain.Price;
import com.example.marketapi.product.domain.Reservation;
import com.example.marketapi.transact.model.TransactState;
import com.querydsl.core.annotations.QueryProjection;

public record BuyProductResponse(Long productId,
                                 Reservation reservation,
                                 String name,
                                 Integer price,
                                 TransactState transactState) {

    @QueryProjection
    public BuyProductResponse(Long productId,
                              Reservation reservation,
                              String name,
                              Price price,
                              TransactState transactState) {
        this(productId, reservation, name, price.value(), transactState);
    }
}
