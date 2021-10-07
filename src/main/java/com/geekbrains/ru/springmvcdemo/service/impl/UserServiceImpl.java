package com.geekbrains.ru.springmvcdemo.service.impl;

import com.geekbrains.ru.springmvcdemo.domain.dto.UserDto;
import com.geekbrains.ru.springmvcdemo.domain.entity.RoleEntity;
import com.geekbrains.ru.springmvcdemo.domain.entity.UserEntity;
import com.geekbrains.ru.springmvcdemo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    //Наш кастомный юзер
    public Optional<UserDto> findByUsername(String username) {
        Optional<UserEntity> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return Optional.of(UserDto.builder()
                    .id(user.get().getId())
                    .username(user.get().getUsername())
                    .cart(user.get().getCartDto())
                    .build());
        }else{
            return Optional.empty();
        }
    }

    //Юзер с минимальными требуемыми данными для спринга.
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", username)));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<RoleEntity> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    public boolean saveUser(UserEntity user) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            return false;
        }
        user.setRoles(Collections.singleton(new RoleEntity(1L, "ROLE_USER")));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    public Page<UserEntity> findAllByPage(Pageable pageRequest) {
        return userRepository.findAll(pageRequest);
    }

    public Optional<UserEntity> findById(Long id) {
        return userRepository.findById(id);
    }

    @Transactional
    public void lockUser(Long id) {
        Optional<UserEntity> user = userRepository.findById(id);
        user.get().setAccountNonLocked(false);
        saveUser(user.get());
    }

    @Transactional
    public void disable(Long id) {
        Optional<UserEntity> user = userRepository.findById(id);
        user.get().setEnabled(false);
        saveUser(user.get());
    }

    @Transactional
    public void enable(Long id) {
        Optional<UserEntity> user = userRepository.findById(id);
        user.get().setEnabled(true);
        saveUser(user.get());
    }
}