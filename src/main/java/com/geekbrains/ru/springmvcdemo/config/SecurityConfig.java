package com.geekbrains.ru.springmvcdemo.config;

import com.geekbrains.ru.springmvcdemo.service.impl.UserServiceImpl;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Data
@Configuration
@EnableWebSecurity //отключает стандартные настройки безопасности Spring Security и начинает использовать правила, прописанные в SecurityConfig
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true) //активирует возможность ставить защиту на уровне методов (для этого над методами ставятся аннотации @Secured и @PreAuthorized).
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserServiceImpl userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/auth_page/**").authenticated()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/product/manager/**").hasAnyRole("MANAGER", "ADMIN")
                .anyRequest().permitAll()
                    .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .failureUrl("/login-error")
                    .and()
                .logout().permitAll()
                .logoutSuccessUrl("/product").permitAll()
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
