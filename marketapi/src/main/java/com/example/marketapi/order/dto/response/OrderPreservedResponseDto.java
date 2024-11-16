package com.example.marketapi.order.dto.response;

import com.querydsl.core.Tuple;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

import static com.example.marketapi.order.domain.QOrder.order;
import static com.example.marketapi.product.domain.QProduct.product;

@Builder
public class OrderPreservedResponseDto {
    private String productName;
    private String price;
    private String sellerName;

    public static OrderPreservedResponseDto of(Tuple tuple) {
        return OrderPreservedResponseDto.builder()
                .productName(tuple.get(
                        product.name))
                .price(tuple.get(product.price))
                .sellerName(tuple.get(order.sellerName))
                .build();
    }

    public static List<OrderPreservedResponseDto> of(List<Tuple> products) {
        List<OrderPreservedResponseDto> list = new ArrayList<>();

        for (Tuple tuple:products) {
            list.add(OrderPreservedResponseDto.of(tuple));
        }

        return list;
    }
}
