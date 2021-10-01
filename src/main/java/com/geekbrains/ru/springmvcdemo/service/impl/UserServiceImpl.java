package com.geekbrains.ru.springmvcdemo.service.impl;

import com.geekbrains.ru.springmvcdemo.config.SecurityConfig;
import com.geekbrains.ru.springmvcdemo.domain.entity.RoleEntity;
import com.geekbrains.ru.springmvcdemo.domain.entity.UserEntity;
import com.geekbrains.ru.springmvcdemo.repository.RoleRepository;
import com.geekbrains.ru.springmvcdemo.repository.UserRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.servlet.view.RedirectView;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    //Наш кастомный юзер
    public Optional<UserEntity> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    //Юзер с минимальными требуемыми данными для спринга.
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", username)));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<RoleEntity> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    public User findByUserName(String name) throws UsernameNotFoundException {
        Optional<UserEntity> user = userRepository.findByUsername(name);
        if (user.isPresent()) {
            return new User(user.get().getUsername(), user.get().getPassword(), mapRolesToAuthorities(user.get().getRoles()));
        }
        throw new UsernameNotFoundException("Invalid username or password");
    }

    public boolean saveUser(UserEntity user) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if (userRepository.findByUsername(user.getUsername()).isPresent()){
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

    public Optional<UserEntity> findById(Long id){
        return userRepository.findById(id);
    }

    @Transactional
    public void lockUser(Long id) {
        Optional<UserEntity> user = userRepository.findById(id);
        user.get().setAccountNonLocked(false);
        saveUser(user.get());
    }
}