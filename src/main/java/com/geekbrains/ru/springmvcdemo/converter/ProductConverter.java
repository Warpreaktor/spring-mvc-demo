package com.geekbrains.ru.springmvcdemo.converter;

import com.geekbrains.ru.springmvcdemo.domain.entity.ProductEntity;
import com.geekbrains.ru.springmvcdemo.domain.dto.ProductDto;
import com.geekbrains.ru.springmvcdemo.soap.product.Product;

import java.math.BigInteger;
import java.util.List;
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

    public static Product convertToWsdl(ProductEntity entity){
        Product product = new Product();
        product.setId(entity.getId());
        product.setTitle(entity.getTitle());
        product.setPrice(BigInteger.valueOf(entity.getPrice()));
        return product;
    }

    public static List<Product> convertToWsdl(List<ProductEntity> entities){
        return entities.stream()
                .map(ProductConverter::convertToWsdl)
                .collect(Collectors.toList());
    }
}
