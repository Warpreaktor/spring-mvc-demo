package com.geekbrains.ru.springmvcdemo.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping()
public class HomePageController {

    @GetMapping
    public String homePage() {
        return "redirect:/product";
    }
}
