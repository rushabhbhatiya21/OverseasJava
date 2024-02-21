package com.example.task_5_1.services;

import com.example.task_5_1.model.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final IUserRepository userRepository;

    public List<UserDao> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<UserDao> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public void saveUser(UserDao user) {
        userRepository.save(user);
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
