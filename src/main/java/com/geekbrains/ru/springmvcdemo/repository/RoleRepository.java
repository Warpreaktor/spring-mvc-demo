package com.geekbrains.ru.springmvcdemo.repository;

import com.geekbrains.ru.springmvcdemo.domain.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
}
