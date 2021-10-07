package com.geekbrains.ru.springmvcdemo.controller;

import com.geekbrains.ru.springmvcdemo.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.geekbrains.ru.springmvcdemo.domain.constant.RequestNameConstant.API_V1;
import static com.geekbrains.ru.springmvcdemo.domain.constant.RequestNameConstant.CART;

@RestController
@AllArgsConstructor
@RequestMapping(API_V1 + CART)
public class CartControllerRest {

    private final CartService cartService;

    @GetMapping("/add")
    public int addProductGet(@RequestParam(name = "id") Long id){
        return cartService.add(id);
    }
}
