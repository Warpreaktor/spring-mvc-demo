package com.geekbrains.ru.springmvcdemo.repository;

import com.geekbrains.ru.springmvcdemo.domain.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    Optional<CategoryEntity> findByAlias(String alias);

}
