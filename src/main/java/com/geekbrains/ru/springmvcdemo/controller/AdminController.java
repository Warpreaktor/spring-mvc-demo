package com.geekbrains.ru.springmvcdemo.controller;

import com.geekbrains.ru.springmvcdemo.domain.entity.UserEntity;
import com.geekbrains.ru.springmvcdemo.service.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final UserServiceImpl userService;

    @GetMapping("/users")
    public String getUserList(Model model,
                       @RequestParam(value = "pageNum", required = false) Integer pageNum){

        final int pageSize = 15;
        Page<UserEntity> usersPage;

        Pageable pageRequest = PageRequest.of(pageNum == null ? 0 : pageNum, pageSize);

        usersPage = userService.findAllByPage(pageRequest);

        ArrayList<Integer> pageNumbers = new ArrayList<>();
        for (int i = 0; i < usersPage.getTotalPages(); i++) {
            pageNumbers.add(i);
        }
        model.addAttribute("pageNumbers", pageNumbers);
        model.addAttribute("usersPage", usersPage);
        model.addAttribute("userEntity", new UserEntity());
        return "/admin/user_list";
    }

    @GetMapping("/lock-user")
    public String lockUser(@RequestParam(value = "id")Long id){
        userService.lockUser(id);
        return "redirect:/admin/users";
    }
}
