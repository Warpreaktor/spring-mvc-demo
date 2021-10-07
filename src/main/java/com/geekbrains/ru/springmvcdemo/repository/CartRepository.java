package com.geekbrains.ru.springmvcdemo.repository;

import com.geekbrains.ru.springmvcdemo.domain.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Long> {

    Optional<CartEntity> findCartEntityByProductIdAndOwnerId(Long productId, Long ownerId);

    Set<CartEntity> findAllByOwnerId(Long ownerId);

}
