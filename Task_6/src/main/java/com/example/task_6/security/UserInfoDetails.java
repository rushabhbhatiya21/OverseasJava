package com.example.task_6.security;

import com.example.task_6.model.UserDao;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserInfoDetails implements UserDetails {
    @Getter
    private final Long id;
    private final String username;
    private final String password;
    private final List<GrantedAuthority> roles;

    public UserInfoDetails(UserDao user){
        this.id = user.getId();
        this.username = user.getName();
        this.password = user.getPassword();
        this.roles = Arrays.stream(new String[]{user.getRole().toString()})
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
