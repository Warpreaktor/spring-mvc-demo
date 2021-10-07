package com.geekbrains.ru.springmvcdemo.domain.dto;

import com.geekbrains.ru.springmvcdemo.domain.entity.CartEntity;
import com.geekbrains.ru.springmvcdemo.domain.entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {

    private Long id;
    private ProductEntity product;
    private int quantity;

}
