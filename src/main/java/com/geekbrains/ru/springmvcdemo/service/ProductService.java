package com.geekbrains.ru.springmvcdemo.service;

import com.geekbrains.ru.springmvcdemo.domain.entity.ProductEntity;
import com.geekbrains.ru.springmvcdemo.domain.ProductSearchCondition;
import com.geekbrains.ru.springmvcdemo.domain.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<ProductEntity> findAll();

    Page<ProductEntity>  findAllByPriceLessThanEqual(Integer maxPrice, Pageable pageable);

    Page<ProductEntity>  findAllByPriceGreaterThanEqual(Integer minPrice, Pageable pageable);

    Page<ProductEntity> findAllBySearchCondition(ProductSearchCondition searchCondition);

    Page<ProductEntity> findAllByPageAndCategory(Pageable pageable, String categoryAlias);

    Page<ProductEntity>  findAllByPriceLessThanEqualAndPriceGreaterThanEqual(Integer maxPrice, Integer minPrice, Pageable pageable);

    ProductDto findByIdDto(long id);

    Optional<ProductEntity> findById(long id);

    Page<ProductEntity> findByTitle(String name, Pageable pageable);

    ProductEntity save(ProductEntity productEntity);

    Page<ProductEntity> findAllByPage(Pageable pageable);

    ProductEntity saveWithImage(ProductEntity product, MultipartFile image);

    void deleteById(Long id);
}
