package com.geekbrains.ru.springmvcdemo.converter;

import com.geekbrains.ru.springmvcdemo.domain.entity.CategoryEntity;
import com.geekbrains.ru.springmvcdemo.domain.dto.CategoryDto;

import java.util.Set;
import java.util.stream.Collectors;

public class CategoryConverter {

    public static Set<CategoryDto> convertToDto(Set<CategoryEntity> entities){
        return entities.stream()
                .map(CategoryConverter::convertToDto)
                .collect(Collectors.toSet());
    }

    public static CategoryDto convertToDto(CategoryEntity entity){
        return CategoryDto
                .builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }
}
