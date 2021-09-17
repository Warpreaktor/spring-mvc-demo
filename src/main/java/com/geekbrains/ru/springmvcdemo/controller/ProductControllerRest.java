package com.geekbrains.ru.springmvcdemo.controller;

import com.geekbrains.ru.springmvcdemo.domain.ProductEntity;
import com.geekbrains.ru.springmvcdemo.error.ProductErrorResponse;
import com.geekbrains.ru.springmvcdemo.error.ProductNotFoundException;
import com.geekbrains.ru.springmvcdemo.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/api/v1/product")
@RestController
public class ProductControllerRest {

    private ProductService productService;


    @GetMapping("/{id}")
    public ProductEntity getStudentById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @GetMapping
    public List<ProductEntity> getAllProducts() {
        return productService.findAll();
    }

    @PostMapping
    public ProductEntity addProduct(@RequestBody ProductEntity product) {
        return productService.save(product);
    }

    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ProductEntity updateStudent(@RequestBody ProductEntity product) {
        return productService.save(product);
    }

    @DeleteMapping("/{id}")
    public int deleteStudent(@PathVariable Long id) {
        productService.deleteById(id);
        return HttpStatus.OK.value();
    }

    @ExceptionHandler
    public ResponseEntity<ProductErrorResponse> handleException(ProductNotFoundException exc) {
        ProductErrorResponse productErrorResponse = new ProductErrorResponse();
        productErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        productErrorResponse.setMessage(exc.getMessage());
        productErrorResponse.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(productErrorResponse, HttpStatus.NOT_FOUND);
    }

}
