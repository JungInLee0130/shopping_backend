package com.example.marketapi.product.response;

import com.example.marketapi.product.domain.Reservation;
import com.example.marketapi.product.entity.Product;

import java.util.List;

public record ProductDetailResponse(String productName,
                                    int price,
                                    Reservation reservation,
                                    List<BuyInfo> buyInfos) {
    public ProductDetailResponse(Product product, List<BuyInfo> buyInfos) {
        this(product.getName(), product.getPrice().value(),
                product.getReservation(), buyInfos);
    }
}
