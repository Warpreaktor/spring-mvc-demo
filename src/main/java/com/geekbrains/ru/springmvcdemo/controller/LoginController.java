package com.geekbrains.ru.springmvcdemo.controller;

import com.geekbrains.ru.springmvcdemo.domain.entity.UserEntity;
import com.geekbrains.ru.springmvcdemo.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

import static com.geekbrains.ru.springmvcdemo.domain.constant.RequestNameConstant.API_V1;
import static com.geekbrains.ru.springmvcdemo.domain.constant.RequestNameConstant.PRODUCT;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final UserServiceImpl userService;

    //TODO добавить домашнюю страничку
    @GetMapping("/")
    public RedirectView homePage() {
       // SecurityContextHolder.getContext().getAuthentication();
        return new RedirectView("/product");
    }

    @GetMapping("/login")
    public String getLoginForm() {
        return "/users/login";
    }

    @GetMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        model.addAttribute("userExists", true);
        return "/users/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            request.getSession().invalidate();
        }
        return "redirect:/";
    }

//    //TODO Добавить админскую панель
//    @GetMapping("/admin")
//    public String adminPage() {
//        return "admin";
//    }


//    @GetMapping("/user_info")
//    public String daoTestPage(Principal principal) {
//        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("Unable to find user by username: " + principal.getName()));
//        return "Authenticated user info: " + user.getUsername() + " : " + user.getEmail();
//    }
}
