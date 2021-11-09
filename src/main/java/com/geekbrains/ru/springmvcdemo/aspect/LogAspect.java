package com.geekbrains.ru.springmvcdemo.aspect;

import com.geekbrains.ru.springmvcdemo.domain.entity.ProductEntity;
import com.geekbrains.ru.springmvcdemo.domain.entity.UserEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

    private static final Logger LOGGER = LogManager.getLogger(LogAspect.class.getName());

    @AfterReturning("execution(* com.geekbrains.ru.springmvcdemo.service.impl.ProductServiceImpl.save(..))")
    public void afterSave(JoinPoint joinPoint){
        ProductEntity product = (ProductEntity) joinPoint.getArgs()[0];
        LOGGER.warn("Product \"" + product.getTitle() + "\" added in shop");
    }

    @AfterReturning("execution(* com.geekbrains.ru.springmvcdemo.service.impl.ProductServiceImpl.delete(..))")
    public void afterDelete(JoinPoint joinPoint){
        ProductEntity product = (ProductEntity) joinPoint.getArgs()[0];
        LOGGER.warn("Product \"" + product.getTitle() + "\" deleted");
    }

//    @AfterReturning("execution(* com.geekbrains.ru.springmvcdemo.service.impl.UserServiceImpl.loadUserByUsername(..))")
//    public void afterLogin(JoinPoint joinPoint){
//        UserEntity user = (UserEntity) joinPoint.getArgs()[0];
//        LOGGER.warn("User " + user + " logged in");
//    }

}
