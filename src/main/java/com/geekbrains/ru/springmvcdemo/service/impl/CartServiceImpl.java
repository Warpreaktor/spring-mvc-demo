package com.geekbrains.ru.springmvcdemo.service.impl;

import com.geekbrains.ru.springmvcdemo.converter.CartConverter;
import com.geekbrains.ru.springmvcdemo.domain.dto.CartDto;
import com.geekbrains.ru.springmvcdemo.domain.dto.UserDto;
import com.geekbrains.ru.springmvcdemo.domain.entity.CartEntity;
import com.geekbrains.ru.springmvcdemo.domain.entity.ProductEntity;
import com.geekbrains.ru.springmvcdemo.repository.CartRepository;
import com.geekbrains.ru.springmvcdemo.service.CartService;
import com.geekbrains.ru.springmvcdemo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductService productService;
    private final UserServiceImpl userService;

    @Override
    public Set<CartDto> findAllDtoByOwnerId(Long ownerId){
        Set<CartEntity> cartEntities = cartRepository.findAllByOwnerId(ownerId);
        return CartConverter.convertToDto(cartEntities);
    }

    @Override
    public int add(Long productId) {
        //Инициализация пользователя
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<UserDto> user = userService.findByUsername(userName);
        Optional<CartEntity> userCartEntity = cartRepository.findCartEntityByProductIdAndOwnerId(productId, user.get().getId());
        if (userCartEntity.isPresent()) {
            userCartEntity.get().setQuantity(userCartEntity.get().getQuantity() + 1);
            cartRepository.save(userCartEntity.get());
            return HttpStatus.OK.value();
        } else {
            cartRepository.save(CartEntity.builder()
                    .owner(userService.findById(user.get().getId()).get())
                    .product(productService.findById(productId).get())
                    .quantity(1)
                    .build());
            return HttpStatus.OK.value();
        }
    }
}
