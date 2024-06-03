package com.example.marketapi.product.dto.request;

import com.example.marketapi.product.domain.Preserved;
import com.example.marketapi.product.domain.Product;
import lombok.Getter;

@Getter
public class ProductRequestDto {
    private String name;
    private String price;
    private Preserved preserved;

    public Product toEntity(){
        return Product.builder()
                .name(name)
                .price(price)
                .preserved(preserved)
                .build();
    }
}
