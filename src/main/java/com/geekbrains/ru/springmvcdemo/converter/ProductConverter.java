package com.geekbrains.ru.springmvcdemo.converter;

import com.geekbrains.ru.springmvcdemo.domain.CategoryEntity;
import com.geekbrains.ru.springmvcdemo.domain.ProductEntity;
import com.geekbrains.ru.springmvcdemo.domain.dto.CategoryDto;
import com.geekbrains.ru.springmvcdemo.domain.dto.ProductDto;
import com.geekbrains.ru.springmvcdemo.service.ProductService;

import java.util.Set;
import java.util.stream.Collectors;

public class ProductConverter {

    public static Set<ProductDto> convertToDto(Set<ProductEntity> entities){
        return entities.stream()
                .map(ProductConverter::convertToDto)
                .collect(Collectors.toSet());
    }

    public static ProductDto convertToDto(ProductEntity entity) {
        return ProductDto
                .builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .price(entity.getPrice())
                .categories(CategoryConverter.convertToDto(entity.getCategories()))
                .build();
    }
}
