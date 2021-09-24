package com.geekbrains.ru.springmvcdemo.controller;

import com.geekbrains.ru.springmvcdemo.domain.dto.CategoryDto;
import com.geekbrains.ru.springmvcdemo.domain.entity.CategoryEntity;
import com.geekbrains.ru.springmvcdemo.error.CategoryErrorResponse;
import com.geekbrains.ru.springmvcdemo.error.CategoryNotFoundException;
import com.geekbrains.ru.springmvcdemo.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.geekbrains.ru.springmvcdemo.domain.constant.RequestNameConstant.*;

@AllArgsConstructor
@RequestMapping(API_V1 + CATEGORY)
@RestController
public class CategoryControllerRest {

    private CategoryService categoryService;

    @GetMapping
    public List<CategoryEntity> getAllCategories() {
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public CategoryDto getCategoryById(@PathVariable(name = "id") Long id) {
        return categoryService.findByIdDto(id);
    }

    @PostMapping("/new")
    public int addCategory(@RequestBody CategoryEntity category) {
        return categoryService.save(category);
    }

    @PutMapping()
    public int updateCategory(@RequestBody CategoryEntity category) {
        return categoryService.update(category);
    }

    @DeleteMapping("/{id}")
    public int deleteCategory(@PathVariable Long id) {
        categoryService.deleteById(id);
        return HttpStatus.OK.value();
    }

    @ExceptionHandler
    public ResponseEntity<CategoryErrorResponse> handleException(CategoryNotFoundException exc) {
        CategoryErrorResponse categoryErrorResponse = new CategoryErrorResponse();
        categoryErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        categoryErrorResponse.setMessage(exc.getMessage());
        categoryErrorResponse.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(categoryErrorResponse, HttpStatus.NOT_FOUND);
    }

}
