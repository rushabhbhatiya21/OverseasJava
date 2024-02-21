package com.example.overseas.controller;

import com.example.overseas.model.UserDao;
import com.example.overseas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private final UserRepository userRepository;

    public RegisterController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public String registerUser(
            @RequestParam String name,
            @RequestParam String password,
            @RequestParam String email,
            @RequestParam(required = false) String specification,
            @RequestParam String role
    ) {
        UserDao user = new UserDao();
        user.setUsername(name);
        user.setPassword(password);
        user.setEmail(email);
        user.setSpecification(specification);
        user.setRole(UserDao.Role.valueOf(role));

        userRepository.save(user);

        return "redirect:/auth/login";
    }
}
