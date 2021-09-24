package com.geekbrains.ru.springmvcdemo.service.impl;

import com.geekbrains.ru.springmvcdemo.converter.CategoryConverter;
import com.geekbrains.ru.springmvcdemo.domain.entity.CategoryEntity;
import com.geekbrains.ru.springmvcdemo.domain.dto.CategoryDto;
import com.geekbrains.ru.springmvcdemo.domain.dto.CategoryTree;
import com.geekbrains.ru.springmvcdemo.repository.CategoryRepository;
import com.geekbrains.ru.springmvcdemo.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

//    private final ProductService productService;
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
    public CategoryDto findByIdDto(Long id) {
        CategoryEntity entity = categoryRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return CategoryDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    @Override
    public CategoryEntity findByAlias(String alias) {
        return categoryRepository.findByAlias(alias).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public int save(CategoryEntity category) {
        categoryRepository.save(category);
        return HttpStatus.OK.value();
    }

    @Transactional
    @Override
    public int update(CategoryEntity entity){
        Optional<CategoryEntity> categoryEntity = Optional.empty();
        if (entity.getId() != null){
            categoryEntity = categoryRepository.findById(entity.getId());
        }
        if (categoryEntity.isPresent()){
           categoryRepository.save(entity);
            return HttpStatus.OK.value();
        } else if (!categoryEntity.isPresent()){
            return HttpStatus.NOT_FOUND.value();
        }
        return HttpStatus.BAD_REQUEST.value();
    }

    @Override
    public Page<CategoryEntity> findAllByPage(Pageable pageable) {
        Page<CategoryEntity> categoryEntities = categoryRepository.findAll(pageable);
        return categoryEntities;
    }

    @Override
    public Page<CategoryDto> findAllByPageDto(Pageable pageable) {
        Page<CategoryEntity> categoryEntities = categoryRepository.findAll(pageable);
        categoryEntities.stream()
                .forEach(entity ->
                        CategoryDto.builder()
                                .id(entity.getId())
                                .name(entity.getName())
                                .build());
        return (Page)categoryEntities;
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

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
