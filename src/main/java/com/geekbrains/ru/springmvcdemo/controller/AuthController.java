package com.geekbrains.ru.springmvcdemo.controller;

import com.geekbrains.ru.springmvcdemo.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final UserServiceImpl userService;

    //TODO добавить домашнюю страничку
    @GetMapping("/")
    public String homePage() {
        return "home";
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


//    @PostMapping("/login")
//    public String login(Model model, UserEntity user,
//                        @ModelAttribute("values") ArrayList<String> values,
//                        @RequestParam(value = "password") String password,
//                        @RequestParam(value = "email", required = false) String email,
//                        RedirectAttributes attributes) {
//        Optional<UserEntity> userEntity = userService.findByUsername(user.getUsername());
//        if (userEntity.isPresent()){
//            return "/product"
//        }else{
//            model.addAttribute("loginError", true);
//            return "/users/login";
//        }
//    }

    //TODO Добавить админскую панель
    @GetMapping("/admin")
    public String adminPage() {
        return "admin";
    }


//    @GetMapping("/user_info")
//    public String daoTestPage(Principal principal) {
//        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("Unable to find user by username: " + principal.getName()));
//        return "Authenticated user info: " + user.getUsername() + " : " + user.getEmail();
//    }
}
