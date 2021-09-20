package com.geekbrains.ru.springmvcdemo.service;

import com.geekbrains.ru.springmvcdemo.domain.entity.CartEntity;
import com.geekbrains.ru.springmvcdemo.domain.entity.ProductEntity;

import java.util.Map;
import java.util.Optional;

public interface CartService {

    Map<ProductEntity, Integer> findAll();

    Optional<CartEntity> findCartEntityByProductId(Long id);

    void save(CartEntity cart);



}
