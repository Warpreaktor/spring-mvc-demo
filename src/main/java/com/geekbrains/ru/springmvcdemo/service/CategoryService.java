package com.geekbrains.ru.springmvcdemo.service;

import com.geekbrains.ru.springmvcdemo.domain.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {

    List<CategoryEntity> findAll();

    CategoryEntity findById(Long id);

    CategoryEntity findByAlias(String alias);

    CategoryEntity save(CategoryEntity category);

    Page<CategoryEntity> findAllByPage(Pageable pageable);
}
