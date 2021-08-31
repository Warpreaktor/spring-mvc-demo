package com.geekbrains.ru.springmvcdemo.controller;

import com.geekbrains.ru.springmvcdemo.domain.CategoryEntity;
import com.geekbrains.ru.springmvcdemo.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private CategoryService categoryService;

//    @GetMapping
//    public String getCategoryByAlias(@RequestParam("alias") String categoryAlias, Model model) {
//        CategoryEntity categoryByAlias = categoryService.findByAlias(categoryAlias);
//
//        model.addAttribute("category", categoryByAlias);
//
//        return "category/detail";
//    }

    @GetMapping("/list")
    public String findCategories(@RequestParam("pageNum") Integer pageNum, Model model) {
        final int pageSize = 5;

        Page<CategoryEntity> page = categoryService.findAllByPage(PageRequest.of(pageNum, pageSize));

        model.addAttribute("page", page);

        return "category/list";
    }
}
