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

    private static final Logger LOGGER = LogManager.getLogger(LogAspect.class);

//    @Before("execution(* com.geekbrains.ru.springmvcdemo.service.impl.UserServiceImpl.saveUser(user))")
//    public void beforeLogin(UserEntity user){
//        LOGGER.info("User " + user.getUsername() + "try to save");
//    }

    @AfterReturning("execution(* com.geekbrains.ru.springmvcdemo.service.impl.ProductServiceImpl.save())")
    public void beforeSave(JoinPoint joinPoint){
        ProductEntity product = (ProductEntity) joinPoint.getArgs()[1];
        LOGGER.info("Product " + product.getTitle() + " added in shop");
        LOGGER.error("ABRACADABRA");
    }

}
