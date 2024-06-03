package com.example.marketapi.product.dto.response;

import com.example.marketapi.product.domain.Preserved;
import com.example.marketapi.product.domain.Product;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class ProductResponseDto {
    private String name;
    private String price;
    private Preserved preserved;

    public static ProductResponseDto of(Product product){
        return ProductResponseDto.builder()
                .name(product.getName())
                .price(product.getPrice())
                .preserved(product.getPreserved())
                .build();

    }

    public static List<ProductResponseDto> of(List<Product> products){
        List<ProductResponseDto> list = new ArrayList<>();
        for (Product product : products) {
            list.add(ProductResponseDto.of(product));
        }
        return list;
    }
}
