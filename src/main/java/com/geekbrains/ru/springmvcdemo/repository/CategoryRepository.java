package com.geekbrains.ru.springmvcdemo.repository;

import com.geekbrains.ru.springmvcdemo.domain.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    Optional<CategoryEntity> findByAlias(String alias);

    /**
     * Через нижнее подчеркивание в SpringData можно обратиться к полю найденной сущности.
     * Т.е. это что-то вроде where
     */
    Set<CategoryEntity> findByProducts_Id(Long id);

}
