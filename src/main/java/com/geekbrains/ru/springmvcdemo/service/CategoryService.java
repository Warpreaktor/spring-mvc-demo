package com.geekbrains.ru.springmvcdemo.service;

import com.geekbrains.ru.springmvcdemo.domain.CategoryEntity;
import com.geekbrains.ru.springmvcdemo.domain.dto.CategoryDto;
import com.geekbrains.ru.springmvcdemo.domain.dto.CategoryTree;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface CategoryService {

    List<CategoryEntity> findAll();

    CategoryEntity findById(Long id);

    CategoryEntity findByAlias(String alias);

    CategoryEntity save(CategoryEntity category);

    Page<CategoryEntity> findAllByPage(Pageable pageable);

    CategoryTree getCategoryTree();

    Set<CategoryDto> findCategoryByProductId(Long id);
}
