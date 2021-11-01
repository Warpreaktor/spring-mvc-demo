package com.geekbrains.ru.springmvcdemo.service;

import com.geekbrains.ru.springmvcdemo.domain.dto.CartDto;
import org.springframework.http.HttpStatus;

import java.util.Set;

public interface CartService {

    Set<CartDto> findAllDtoByOwnerId(Long ownerId);

    HttpStatus add(Long productId);


}
