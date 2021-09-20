package com.geekbrains.ru.springmvcdemo.service.impl;

import com.geekbrains.ru.springmvcdemo.domain.entity.CartEntity;
import com.geekbrains.ru.springmvcdemo.domain.entity.ProductEntity;
import com.geekbrains.ru.springmvcdemo.repository.CartRepository;
import com.geekbrains.ru.springmvcdemo.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    @Override
    public Map<ProductEntity, Integer> findAll() {
        Map<ProductEntity, Integer> cart = new HashMap<>();
        for(CartEntity obj : cartRepository.findAll()){
            cart.put(obj.getProduct(), obj.getQuantity());
        }
        return cart;
    }

    @Override
    public void save(CartEntity cart) {
        cartRepository.save(cart);
    }

    @Override
    public Optional<CartEntity> findCartEntityByProductId(Long id) {
        return cartRepository.findCartEntityByProductId(id);
    }
}
