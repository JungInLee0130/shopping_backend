package com.example.marketapi.product.response;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.example.marketapi.product.response.QBuyProductResponse is a Querydsl Projection type for BuyProductResponse
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QBuyProductResponse extends ConstructorExpression<BuyProductResponse> {

    private static final long serialVersionUID = -210524777L;

    public QBuyProductResponse(com.querydsl.core.types.Expression<Long> productId, com.querydsl.core.types.Expression<com.example.marketapi.product.domain.Reservation> reservation, com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<com.example.marketapi.product.domain.Price> price, com.querydsl.core.types.Expression<com.example.marketapi.transact.model.TransactState> transactState) {
        super(BuyProductResponse.class, new Class<?>[]{long.class, com.example.marketapi.product.domain.Reservation.class, String.class, com.example.marketapi.product.domain.Price.class, com.example.marketapi.transact.model.TransactState.class}, productId, reservation, name, price, transactState);
    }

}

