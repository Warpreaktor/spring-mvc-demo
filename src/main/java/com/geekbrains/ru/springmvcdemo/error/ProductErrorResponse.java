package com.geekbrains.ru.springmvcdemo.error;

import lombok.Data;

@Data
public class ProductErrorResponse  {

    private int status;
    private String message;
    private long timestamp;

    public ProductErrorResponse() {
    }
}


