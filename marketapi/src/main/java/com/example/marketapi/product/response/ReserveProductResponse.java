package com.example.marketapi.product.response;

import com.example.marketapi.product.domain.Price;
import com.querydsl.core.annotations.QueryProjection;

public record ReserveProductResponse(Long productId,
                                     String name,
                                     Integer price) {

    @QueryProjection
    public ReserveProductResponse(Long productId, String name, Price price) {
        this(productId, name, price.value());
    }
}
