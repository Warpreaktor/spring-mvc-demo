package com.geekbrains.ru.springmvcdemo.controller.rest;

import com.geekbrains.ru.springmvcdemo.domain.entity.ProductEntity;
import com.geekbrains.ru.springmvcdemo.domain.ProductSearchCondition;
import com.geekbrains.ru.springmvcdemo.domain.dto.ProductDto;
import com.geekbrains.ru.springmvcdemo.error.ProductErrorResponse;
import com.geekbrains.ru.springmvcdemo.error.ProductNotFoundException;
import com.geekbrains.ru.springmvcdemo.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.geekbrains.ru.springmvcdemo.domain.constant.RequestNameConstant.*;

@AllArgsConstructor
@RequestMapping(API_V1 + PRODUCT)
@RestController
public class ProductControllerRest {

    private ProductService productService;

    @GetMapping("all")
    public List<ProductEntity> getAllProducts() {
        return productService.findAll();
    }

    @GetMapping("/one")
    public ProductDto getProductById(@RequestParam(value = "id") Long id) {
        return productService.findByIdDto(id);
    }

    @PostMapping()
    public Page<ProductEntity> getProductByCondition(@RequestBody ProductSearchCondition searchCondition){
        return productService.findAllBySearchCondition(searchCondition);
    }

    @PostMapping(SAVE)
    public ProductEntity addProduct(@RequestBody ProductEntity product) {
        return productService.save(product);
    }

    @PostMapping(DELETE)
    public int deleteProduct(@RequestBody ProductEntity product) {
        productService.delete(product);
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
