package com.geekbrains.ru.springmvcdemo.repository;

import com.geekbrains.ru.springmvcdemo.domain.CategoryEntity;
import com.geekbrains.ru.springmvcdemo.domain.ProductEntity;
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

    @Query(value = "select * from product where price < ?1 and price > ?2",
            countQuery = "select count(*) from product where price < ?1 and price > ?2",
            nativeQuery = true)
    Page<ProductEntity> findAllByPriceLessThanEqualAndPriceGreaterThanEqual(Integer max, Integer min, Pageable pageable);

    @Query(value = "select * from product where price < ?1",
            countQuery = "select count(*) from product where price < ?1",
            nativeQuery = true)
    Page<ProductEntity> findAllByPriceLessThanEqual(Integer maxPrice, Pageable page);

    @Query(value = "select * from product where title like ?1",
            countQuery = "select count(*) from product where title like ?1",
            nativeQuery = true)
    Page<ProductEntity> findByTitle(String name, Pageable pageable);

    Page<ProductEntity>  findAllByPriceGreaterThanEqual(Integer minPrice, Pageable pageable);

    Page<ProductEntity> findAllByCategories(CategoryEntity category, Pageable pageable);

}
