package com.geekbrains.ru.springmvcdemo.error;

import lombok.Data;

@Data
public class CategoryErrorResponse {

    private int status;
    private String message;
    private long timestamp;

    public CategoryErrorResponse() {
    }
}


