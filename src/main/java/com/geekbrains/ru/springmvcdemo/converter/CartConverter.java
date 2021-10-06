package com.geekbrains.ru.springmvcdemo.converter;

import com.geekbrains.ru.springmvcdemo.domain.dto.CartDto;
import com.geekbrains.ru.springmvcdemo.domain.entity.CartEntity;

import java.util.Set;
import java.util.stream.Collectors;

public class CartConverter {

    public static Set<CartDto> convertToDto(Set<CartEntity> entities){
        return entities.stream()
                .map(CartConverter::convertToDto)
                .collect(Collectors.toSet());
    }

    public static CartDto convertToDto(CartEntity entity) {
        return CartDto
                .builder()
                .id(entity.getId())
                .product(entity.getProduct())
                .quantity(entity.getQuantity())
                .build();
    }
}
