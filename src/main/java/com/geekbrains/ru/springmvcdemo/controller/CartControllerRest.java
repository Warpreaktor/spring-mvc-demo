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

    private final ProductService productService;
    private final CartService cartService;

    @GetMapping
    public Map<ProductEntity, Integer> getProducts(){
        return cartService.findAll();
    }

    @PostMapping("/add/{id}")
    public int addProduct(@PathVariable(name = "id") Long id){
        Optional<CartEntity> entity = cartService.findCartEntityByProductId(id);
        if (entity.isPresent()){
            CartEntity cart = entity.get();
            cart.setQuantity(cart.getQuantity() + 1);
            cartService.save(cart);
            return HttpStatus.OK.value();
        }else{
            CartEntity cart = new CartEntity();
            cart.setProduct(productService.findById(id));
            cart.setQuantity(1);
            cartService.save(cart);
            return HttpStatus.OK.value();
        }
    }
}
