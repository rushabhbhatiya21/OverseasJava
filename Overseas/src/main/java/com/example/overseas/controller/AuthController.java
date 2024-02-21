package com.example.overseas.controller;

import com.example.overseas.model.UserDao;
import com.example.overseas.repository.UserRepository;
import com.example.overseas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;

    private final UserService service;

    @Autowired
    public AuthController(UserRepository userRepository, UserService service) {
        this.userRepository = userRepository;
        this.service = service;
    }

    @PostMapping("/changePassword")
    public ResponseEntity<String> changePassword(
            @RequestParam String oldPassword,
            @RequestParam String newPassword
    )
    {
        Optional<UserDao> loggedInUser = userRepository.findUserDaoByUsername(service.getLoggedInUserDetails().getUsername());

        if (loggedInUser.isPresent()) {
            int id = loggedInUser.get().getId();
            Optional<UserDao> user = userRepository.findUserDaoById(id);
            if (user.isPresent()) {
                if (oldPassword.equals(user.get().getPassword())) {
                    user.get().setPassword(newPassword);
                    userRepository.save(user.get());
                    return ResponseEntity.ok("Password changed successfully");
                }
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Failed to change password");
    }
}
