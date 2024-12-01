package com.example.marketapi.product.response;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.example.marketapi.product.response.QProductPreviewResponse is a Querydsl Projection type for ProductPreviewResponse
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QProductPreviewResponse extends ConstructorExpression<ProductPreviewResponse> {

    private static final long serialVersionUID = -1191073945L;

    public QProductPreviewResponse(com.querydsl.core.types.Expression<String> productName, com.querydsl.core.types.Expression<com.example.marketapi.product.domain.Price> price, com.querydsl.core.types.Expression<com.example.marketapi.product.domain.Reservation> reservation) {
        super(ProductPreviewResponse.class, new Class<?>[]{String.class, com.example.marketapi.product.domain.Price.class, com.example.marketapi.product.domain.Reservation.class}, productName, price, reservation);
    }

}

