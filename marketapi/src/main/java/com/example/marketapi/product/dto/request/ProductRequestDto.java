package com.example.marketapi.product.dto.request;

import com.example.marketapi.product.domain.Preserved;
import com.example.marketapi.product.domain.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductRequestDto {
    private String name; // 제품 등록자
    private String price; // 가격
    private Preserved preserved; // 제품상태

    @Builder
    public ProductRequestDto(String name, String price, Preserved preserved) {
        this.name = name;
        this.price = price;
        this.preserved = preserved;
    }

    @Builder
    public static Product toEntity(ProductRequestDto requestDto){
        return Product.builder()
                .name(requestDto.name)
                .price(requestDto.price)
                .preserved(requestDto.preserved)
                .build();
    }
}
