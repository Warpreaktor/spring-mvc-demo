package com.geekbrains.ru.springmvcdemo.service;

import com.geekbrains.ru.springmvcdemo.domain.entity.CategoryEntity;
import com.geekbrains.ru.springmvcdemo.domain.dto.CategoryDto;
import com.geekbrains.ru.springmvcdemo.domain.dto.CategoryTree;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface CategoryService {

    List<CategoryEntity> findAll();

    CategoryEntity findById(Long id);

    CategoryEntity findByAlias(String alias);

    int save(CategoryEntity category);

    int update(CategoryEntity category);

    Page<CategoryEntity> findAllByPage(Pageable pageable);

    Page<CategoryDto> findAllByPageDto(Pageable pageable);

    CategoryTree getCategoryTree();

    Set<CategoryDto> findCategoryByProductId(Long id);

    CategoryDto findByIdDto(Long id);

    void deleteById(Long id);
}
