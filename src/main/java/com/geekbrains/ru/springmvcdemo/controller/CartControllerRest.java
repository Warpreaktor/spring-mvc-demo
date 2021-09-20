package com.geekbrains.ru.springmvcdemo.controller;

import com.geekbrains.ru.springmvcdemo.domain.entity.CartEntity;
import com.geekbrains.ru.springmvcdemo.domain.entity.ProductEntity;
import com.geekbrains.ru.springmvcdemo.service.CartService;
import com.geekbrains.ru.springmvcdemo.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

import static com.geekbrains.ru.springmvcdemo.domain.constant.RequestNameConstant.API_V1;
import static com.geekbrains.ru.springmvcdemo.domain.constant.RequestNameConstant.CART;

@RestController
@AllArgsConstructor
@RequestMapping(API_V1 + CART)
public class CartControllerRest {

    private final CartService cartService;

    @GetMapping
    public Map<ProductEntity, Integer> getProducts(){
        return cartService.findAll();
    }

    @PostMapping("/add/{id}")
    public int addProduct(@PathVariable(name = "id") Long id){
        return cartService.save(id);
    }
}