package com.geekbrains.ru.springmvcdemo.service.impl;

import com.geekbrains.ru.springmvcdemo.domain.CategoryEntity;
import com.geekbrains.ru.springmvcdemo.repository.CategoryRepository;
import com.geekbrains.ru.springmvcdemo.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryEntity> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public CategoryEntity findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public CategoryEntity findByAlias(String alias) {
        return categoryRepository.findByAlias(alias).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public CategoryEntity save(CategoryEntity category) {
        return null;
    }

    @Override
    public Page<CategoryEntity> findAllByPage(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

}
