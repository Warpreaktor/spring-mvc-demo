package com.geekbrains.ru.springmvcdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

    @Before("execution(* com.geekbrains.ru.springmvcdemo.controller.LoginController.loginError())")
    public void beforeLoginError(){

    }

}
