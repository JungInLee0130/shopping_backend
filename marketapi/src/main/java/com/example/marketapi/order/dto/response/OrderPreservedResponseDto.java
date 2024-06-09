package com.example.marketapi.product.dto.response;

import com.example.marketapi.product.domain.Product;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@Builder
public class ProductPreservedResponseDto {
    private String productName;
    private String price;
    private String sellerName;

    public static ProductPreservedResponseDto of(Product product) {
        return ProductPreservedResponseDto.builder()
                .productName(product.getName())
                .price(product.getPrice())
                .sellerName(product.getSellerName())
                .build();
    }

    public static List<ProductPreservedResponseDto> of(List<Product> products) {
        List<ProductPreservedResponseDto> list = new ArrayList<>();

        for (Product product:products) {
            list.add(ProductPreservedResponseDto.of(product));
        }

        return list;
    }
}
