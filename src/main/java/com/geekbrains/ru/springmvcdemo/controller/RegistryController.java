package com.geekbrains.ru.springmvcdemo.controller;

import com.geekbrains.ru.springmvcdemo.domain.entity.UserEntity;
import com.geekbrains.ru.springmvcdemo.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class RegistryController {

    private final UserServiceImpl userService;

    @GetMapping("/registration")
    public String getRegistrationForm(Model model) {
        model.addAttribute("userForm", new UserEntity());
        return "/users/registration";
    }

    //TODO обработать кейсы:
    //1. Пользователь уже существует
    @PostMapping("/registration")
    public String addUser(@ModelAttribute("userForm") @Valid UserEntity user,
                                BindingResult bindingResult,
                                Model model) {
        if (bindingResult.hasFieldErrors("password")) {
            model.addAttribute("passwordError", true);
            return "/users/registration";
        }
        if (!user.getPassword().equals(user.getPasswordConfirm())){
            model.addAttribute("passwordConfirmError", true);
            return "users/registration";
        }
        if (bindingResult.hasFieldErrors("email")){
            model.addAttribute("emailError", true);
            return "users/registration";
        }
        if (!userService.saveUser(user)){
            model.addAttribute("usernameError", true);
            return "users/registration";
        }
        return "redirect:/product";
    }
}
