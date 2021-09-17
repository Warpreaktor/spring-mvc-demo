package com.geekbrains.ru.springmvcdemo.service.impl;

import com.geekbrains.ru.springmvcdemo.converter.CategoryConverter;
import com.geekbrains.ru.springmvcdemo.domain.CategoryEntity;
import com.geekbrains.ru.springmvcdemo.domain.dto.CategoryDto;
import com.geekbrains.ru.springmvcdemo.domain.dto.CategoryTree;
import com.geekbrains.ru.springmvcdemo.repository.CategoryRepository;
import com.geekbrains.ru.springmvcdemo.service.CategoryService;
import com.geekbrains.ru.springmvcdemo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final ProductService productService;
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

    @Override
    public CategoryTree getCategoryTree() {
        return null;
    }

    public Set<CategoryDto> findCategoryByProductId(Long id){
        Set<CategoryEntity> entities = categoryRepository.findByProducts_Id(id);
        return entities.stream()
                .map(CategoryConverter::convertToDto)
                .collect(Collectors.toSet());
    }

}
