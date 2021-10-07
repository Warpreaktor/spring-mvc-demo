package com.geekbrains.ru.springmvcdemo.controller;

import com.geekbrains.ru.springmvcdemo.domain.dto.CartDto;
import com.geekbrains.ru.springmvcdemo.domain.dto.UserDto;
import com.geekbrains.ru.springmvcdemo.domain.entity.CartEntity;
import com.geekbrains.ru.springmvcdemo.domain.entity.UserEntity;
import com.geekbrains.ru.springmvcdemo.service.CartService;
import com.geekbrains.ru.springmvcdemo.service.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.geekbrains.ru.springmvcdemo.domain.constant.RequestNameConstant.*;

@Controller
@AllArgsConstructor
@RequestMapping(CART)
public class CartController {

    private final CartService cartService;
    private final UserServiceImpl userService;

    @GetMapping
    public String getProducts(Model model){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<UserDto> user = userService.findByUsername(userName);
        Set<CartDto> productsInCart;
        if (user.isPresent()) {
            productsInCart = cartService.findAllDtoByOwnerId(user.get().getId());
            model.addAttribute("cart", productsInCart);
            return "/cart/list";
        }else{
            return "error";
        }
    }

    @GetMapping(ADD)
    public String addProduct(Model model,
                             @RequestParam(name = "productId") Long productId){
        cartService.add(productId);
        return getProducts(model);
    }

}
