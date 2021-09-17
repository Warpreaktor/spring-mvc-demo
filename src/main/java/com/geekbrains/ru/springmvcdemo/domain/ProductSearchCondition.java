package com.geekbrains.ru.springmvcdemo.domain;

import lombok.Data;
import org.springframework.data.domain.Sort;

@Data
public class ProductSearchCondition {
    private Sort.Direction sortDirection = Sort.Direction.ASC;
    private String sortField = "title";

    private int pageNum;
    private Integer pagesSize = 9;
}
