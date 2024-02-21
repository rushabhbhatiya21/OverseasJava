package com.example.overseas.security;

import com.example.overseas.model.UserDao;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CustomUserDetails implements UserDetails {

//    @Getter
//    private final int id;
    private final String username;
    private final String password;
    private final List<GrantedAuthority> roles;

    public CustomUserDetails(UserDao user){
        System.out.println(user.getUsername());
//        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.roles = List.of(new SimpleGrantedAuthority(user.getRole().name()));
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
