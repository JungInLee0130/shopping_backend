package com.example.marketapi.product.dto.request;

import com.example.marketapi.product.domain.Price;
import com.example.marketapi.product.domain.Quantity;
import com.example.marketapi.product.entity.Product;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;


public record ProductRequestDto (@NotBlank String name,
                                 @Min(0) int price,

                                 @Min(1) int quantity) {

    @Builder
    public static Product toEntity(ProductRequestDto requestDto){
        return Product.builder()
                .name(requestDto.name)
                .price(new Price(requestDto.price))
                .quantity(new Quantity(requestDto.quantity))
                .build();
    }
}
