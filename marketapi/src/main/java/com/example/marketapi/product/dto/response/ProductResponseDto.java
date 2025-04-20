package com.example.marketapi.product.dto.response;

import com.example.marketapi.product.domain.Price;
import com.example.marketapi.product.domain.Reservation;
import com.example.marketapi.product.entity.Product;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public record ProductResponseDto(String sellerName,
                                 String productName,
                                 int price,
                                 int quantity,
                                 String reservation) {
    public ProductResponseDto (Product product){
        this(product.getSeller().getName(),
                product.getName(),
                product.getPrice().value(),
                product.getQuantity().value(),
                product.getReservation().name());
    }
    public static List<ProductResponseDto> of (List<Product> products){
        List<ProductResponseDto> productDtoLists = new ArrayList<>();

        for (Product product:products) {
            productDtoLists.add(new ProductResponseDto(product));
        }

        return productDtoLists;
    }
}
