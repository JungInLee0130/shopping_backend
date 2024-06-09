package com.example.marketapi.product.dto.response;

import com.example.marketapi.product.domain.Product;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@Builder
public class ProductPurchasedResponseDto {
    private String productName;
    private String price;
    private String sellerName;

    public static ProductPurchasedResponseDto of(Product product) {
        return ProductPurchasedResponseDto.builder()
                .productName(product.getName())
                .price(product.getPrice())
                .sellerName(product.getSellerName())
                .build();
    }

    public static List<ProductPurchasedResponseDto> of(List<Product> products) {
        List<ProductPurchasedResponseDto> list = new ArrayList<>();

        for (Product product:products) {
            list.add(ProductPurchasedResponseDto.of(product));
        }

        return list;
    }
}
