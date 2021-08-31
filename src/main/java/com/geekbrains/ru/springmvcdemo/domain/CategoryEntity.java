package com.geekbrains.ru.springmvcdemo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "category")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Имя категории обязательно")
    private String name;

    @NotBlank(message = "Алиас категории обязателен")
    private String alias;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private CategoryEntity parentCategory;

    @OneToMany(mappedBy = "parentCategory")
    private Set<CategoryEntity> subCategories;

    @ManyToMany
    @JoinTable(
            name = "product_category",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<ProductEntity> products;
}
