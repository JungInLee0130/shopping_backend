package com.example.marketapi.product.domain;

import jakarta.persistence.AttributeConverter;

public class ProductConverter implements AttributeConverter<Price, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Price attribute) {
        return attribute.value();
    }

    @Override
    public Price convertToEntityAttribute(Integer dbData) {
        return new Price(dbData);
    }
}
