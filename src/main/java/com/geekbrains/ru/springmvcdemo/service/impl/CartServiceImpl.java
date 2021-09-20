package com.geekbrains.ru.springmvcdemo.service.impl;

import com.geekbrains.ru.springmvcdemo.domain.entity.CartEntity;
import com.geekbrains.ru.springmvcdemo.domain.entity.ProductEntity;
import com.geekbrains.ru.springmvcdemo.repository.CartRepository;
import com.geekbrains.ru.springmvcdemo.repository.ProductRepository;
import com.geekbrains.ru.springmvcdemo.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    private final ProductRepository productRepository;

    @Override
    public Map<ProductEntity, Integer> findAll() {
        Map<ProductEntity, Integer> cart = new HashMap<>();
        for(CartEntity obj : cartRepository.findAll()){
            cart.put(obj.getProduct(), obj.getQuantity());
        }
        return cart;
    }

    @Override
    public int save(Long id) {
        Optional<CartEntity> entity = cartRepository.findCartEntityByProductId(id);
        if (entity.isPresent()){
            CartEntity cart = entity.get();
            cart.setQuantity(cart.getQuantity() + 1);
            cartRepository.save(cart);
            return HttpStatus.OK.value();
        }else{
            CartEntity cart = new CartEntity();
            cart.setProduct(productRepository.findById(id).get());
            cart.setQuantity(1);
            cartRepository.save(cart);
            return HttpStatus.OK.value();
        }
    }

    @Override
    public Optional<CartEntity> findCartEntityByProductId(Long id) {
        return cartRepository.findCartEntityByProductId(id);
    }
}
