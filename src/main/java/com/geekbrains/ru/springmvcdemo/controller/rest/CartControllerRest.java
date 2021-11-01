package com.geekbrains.ru.springmvcdemo.controller.rest;

import com.geekbrains.ru.springmvcdemo.domain.dto.ProductDto;
import com.geekbrains.ru.springmvcdemo.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.geekbrains.ru.springmvcdemo.domain.constant.RequestNameConstant.*;

@RestController
@AllArgsConstructor
@RequestMapping(API_V1 + CART)
public class CartControllerRest {

    private final CartService cartService;

    @PostMapping(ADD)
    public HttpStatus addProduct(@RequestBody ProductDto productDto){
        return cartService.add(productDto.getId());
    }
}
