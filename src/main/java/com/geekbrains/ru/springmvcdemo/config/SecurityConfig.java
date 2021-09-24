package com.geekbrains.ru.springmvcdemo.config;

import com.geekbrains.ru.springmvcdemo.service.UserService;
import com.geekbrains.ru.springmvcdemo.service.impl.UserServiceImpl;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

@Data
@Configuration
@EnableWebSecurity //отключает стандартные настройки безопасности Spring Security и начинает использовать правила, прописанные в SecurityConfig
@EnableGlobalMethodSecurity(securedEnabled = true) //активирует возможность ставить защиту на уровне методов (для этого над методами ставятся аннотации @Secured и @PreAuthorized).
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserServiceImpl userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").hasAnyRole("USER")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .and()
                .formLogin()//Страничка SpringSecurity .loginPage сослаться на свою страничку.
                .loginProcessingUrl("/authenticateTheUser")
                .permitAll()
                .and()
                ;
    }
    //Объекты создаются как синглтоны
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userService);
        return authenticationProvider;
    }
}
