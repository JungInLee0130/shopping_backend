package com.example.marketapi.dto.product.request;

import com.example.marketapi.domain.product.Preserved;
import com.example.marketapi.domain.product.Product;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
