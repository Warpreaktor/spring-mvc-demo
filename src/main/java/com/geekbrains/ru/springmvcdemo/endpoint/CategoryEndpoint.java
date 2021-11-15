package com.geekbrains.ru.springmvcdemo.endpoint;

import com.geekbrains.ru.springmvcdemo.converter.CategoryConverter;
import com.geekbrains.ru.springmvcdemo.converter.ProductConverter;
import com.geekbrains.ru.springmvcdemo.domain.entity.CategoryEntity;
import com.geekbrains.ru.springmvcdemo.domain.entity.ProductEntity;
import com.geekbrains.ru.springmvcdemo.service.CategoryService;
import com.geekbrains.ru.springmvcdemo.soap.category.GetAllCategoriesRequest;
import com.geekbrains.ru.springmvcdemo.soap.category.GetAllCategoriesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@RequiredArgsConstructor
public class CategoryEndpoint {

    private static final String NAMESPACE_URI = "http://www.com/geekbrains/ru/springmvcdemo/category";

    private final CategoryService categoryService;

    /*
        Пример запроса: POST http://localhost:8080/ws

        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
          xmlns:f="http://www.com/geekbrains/ru/springmvcdemo/category">
            <soapenv:Header/>
            <soapenv:Body>
                <f:getAllCategoriesRequest/>
            </soapenv:Body>
        </soapenv:Envelope>
     */

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllCategoriesRequest")
    @ResponsePayload
    public GetAllCategoriesResponse getAllCategories(@RequestPayload GetAllCategoriesRequest request) {
        GetAllCategoriesResponse response = new GetAllCategoriesResponse();
        for(CategoryEntity cat : categoryService.findAll()){
            response.getCategories().add(CategoryConverter.convertToWsdl(cat));
        }
        return response;
    }
}

