package com.example.marketapi.product.response;

import com.example.marketapi.product.domain.Price;
import com.example.marketapi.product.domain.Reservation;
import com.querydsl.core.annotations.QueryProjection;

public record ProductPreviewResponse(String productName,
                                     int price,
                                     Reservation reservation) {
    @QueryProjection
    public ProductPreviewResponse(String productName,
                                  Price price,
                                  Reservation reservation) {
        this(productName, price.value(), reservation);
    }
}
