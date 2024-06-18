package com.example.marketapi.product.dto.request;

import com.example.marketapi.product.domain.Preserved;
import com.example.marketapi.product.domain.Product;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductRequestDto {
    private String name; // 제품 등록자
    private String price; // 가격
    private Preserved preserved; // 제품상태

    public Product toEntity(){
        return Product.builder()
                .name(name)
                .price(price)
                .preserved(preserved)
                .build();
    }
}
