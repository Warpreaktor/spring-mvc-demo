package com.geekbrains.ru.springmvcdemo.repository;

import com.geekbrains.ru.springmvcdemo.domain.entity.CategoryEntity;
import com.geekbrains.ru.springmvcdemo.domain.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    //TODO Протестировать эти квери и удалить их нах если все выполняется на Spring Data

    Optional<ProductEntity> findById(Long id);

    Page<ProductEntity> findAllByPriceLessThanEqualAndPriceGreaterThanEqual(Integer max, Integer min, Pageable pageable);

    Page<ProductEntity> findAllByPriceLessThanEqual(Integer maxPrice, Pageable page);

    Page<ProductEntity> findByTitle(String name, Pageable pageable);

    Page<ProductEntity>  findAllByPriceGreaterThanEqual(Integer minPrice, Pageable pageable);

    Page<ProductEntity> findAllByCategories(CategoryEntity category, Pageable pageable);

}
