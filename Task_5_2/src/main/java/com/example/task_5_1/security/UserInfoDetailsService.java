package com.example.task_5_1.security;

import com.example.task_5_1.model.UserDao;
import com.example.task_5_1.services.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Date;
import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class UserInfoDetailsService implements UserDetailsService {

    private final IUserRepository userRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserDao> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            System.out.println(user.get().getUsername());
            System.out.println(user.get().getRole());
            System.out.println(new Date());
        }
        return user.map(UserInfoDetails::new).orElseThrow(()->new UsernameNotFoundException("User Does Not Exist"));
    }
}
