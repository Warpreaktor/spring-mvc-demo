package com.geekbrains.ru.springmvcdemo.service;

import com.geekbrains.ru.springmvcdemo.domain.dto.CartDto;

import java.util.Set;

public interface CartService {

    Set<CartDto> findAllDtoByOwnerId(Long ownerId);

    int add(Long productId);


}
