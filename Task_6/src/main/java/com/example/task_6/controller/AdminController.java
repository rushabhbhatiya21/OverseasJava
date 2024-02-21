package com.example.task_6.controller;


import com.example.task_6.model.UserDao;
import com.example.task_6.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("api/admin")
public class AdminController {

    private final UserService userService;

    @GetMapping
    public String welcome() {
        return "welcome";
    }

    @GetMapping("/adminIndex")
    public String adminHome(Model model) {
        String username = userService.getLoggedInUserDetails().getUsername();
        model.addAttribute("username", username);
        return "admin_index";
    }

    @GetMapping("/signup")
    public String signup() {
        return "registration_form";
    }

    @GetMapping("/adminPage")
    public String loadAdminPage() {
        return "admin_dashboard";
    }

    @PostMapping("/addUser")
    public String addUser(
            @RequestParam String name,
            @RequestParam String role,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String phoneNumber,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String state,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String education,
            @RequestParam(required = false) String company,
            @RequestParam(required = false) MultipartFile photo,
            @RequestParam(required = false) String selfBio
    ) {
        UserDao.Address add = new UserDao.Address(city, state, country);
        UserDao user = new UserDao(name, email, password, phoneNumber, add,  education, photo, company, selfBio, role);
        userService.saveUser(user);
        return "redirect:/api/admin/getUsers";
    }

    @PostMapping("/editUser")
    public String editUser(
            @RequestParam String editId,
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String address,
            @RequestParam String selfBio,
            @RequestParam MultipartFile photo
    ) throws IOException {
        Long newId = Long.parseLong(editId);
        Optional<UserDao> user = userService.getUserById(newId);
        if (user.isPresent())
        {
            UserDao.Address newAddress = userService.createAddress(address);

            if (!Objects.equals(user.get().getName(), name)) user.get().setName(name);
            if (!Objects.equals(user.get().getEmail(), email)) user.get().setEmail(email);
            if (!Objects.equals(user.get().getAddress().toString(), address)) user.get().setAddress(newAddress);
            if (!Objects.equals(user.get().getSelfBio(), selfBio)) user.get().setSelfBio(selfBio);
            user.get().setPhoto(photo);
            userService.saveUser(user.get());
        }
        return "redirect:/api/admin/getUsers";
    }

    @GetMapping("/getUsers")
    public String getUsers(Model model) {
        List<UserDao> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin_dashboard";
    }

    @GetMapping("/deleteUserById")
    public String deleteUser(@RequestParam String id) {
        Long userId = Long.parseLong(id);
        userService.deleteUserById(userId);
        return "redirect:/api/admin/getUsers";

    }
}
