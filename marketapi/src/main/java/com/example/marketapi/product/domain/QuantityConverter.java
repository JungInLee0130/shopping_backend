package com.example.marketapi.product.domain;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/*
* db저장관련 converter
* */
@Converter
public class QuantityConverter implements AttributeConverter<Quantity, Integer> {
    // Quantity -> Integer
    @Override
    public Integer convertToDatabaseColumn(Quantity attribute) {
        return attribute.value();
    }

    // db : integer -> Quantity
    @Override
    public Quantity convertToEntityAttribute(Integer dbData) {
        return new Quantity(dbData);
    }
}
