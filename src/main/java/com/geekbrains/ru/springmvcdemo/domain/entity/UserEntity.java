package com.geekbrains.ru.springmvcdemo.domain.entity;

import com.geekbrains.ru.springmvcdemo.domain.dto.CartDto;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min=2, message = "Логин должен содержать минимум 2 символа")
    @Column(name = "username")
    private String username;

    @Size(min = 6, message = "Пароль должен быть не менее    6 символов")
    @Column(name = "password")
    private String password;

    @Transient
    private String passwordConfirm;

    @Size(min = 1)
    @Column(name = "email")
    private String email;

    @ManyToMany
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<RoleEntity> roles;

    @OneToMany(mappedBy = "owner")
    private List<CartEntity> cart;

    @Column(name = "account_non_locked")
    private boolean accountNonLocked = true;

    @Column(name = "enabled")
    private boolean enabled = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public List<CartDto> getCartDto(){
        ArrayList<CartDto> cartDtoList = new ArrayList();
        for(CartEntity i : cart) {
            CartDto cartDto = CartDto.builder()
                    .product(i.getProduct())
                    .quantity(i.getQuantity())
                    .build();
            cartDtoList.add(cartDto);
        }
        return cartDtoList;
    }
}