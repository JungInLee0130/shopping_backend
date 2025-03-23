package com.example.marketapi.product.response;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.example.marketapi.product.response.QReserveProductResponse is a Querydsl Projection type for ReserveProductResponse
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QReserveProductResponse extends ConstructorExpression<ReserveProductResponse> {

    private static final long serialVersionUID = 567084577L;

    public QReserveProductResponse(com.querydsl.core.types.Expression<Long> productId, com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<com.example.marketapi.product.domain.Price> price) {
        super(ReserveProductResponse.class, new Class<?>[]{long.class, String.class, com.example.marketapi.product.domain.Price.class}, productId, name, price);
    }

}

