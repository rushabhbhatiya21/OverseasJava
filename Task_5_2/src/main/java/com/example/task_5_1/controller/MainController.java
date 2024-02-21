package com.example.task_5_1.controller;

import com.example.task_5_1.model.UserDao;
import com.example.task_5_1.services.UserService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
@RequestMapping("api/home")
public class MainController {

    @Autowired
    private final UserService userService;

    @GetMapping
    public String welcome() {
        return "welcome";
    }

    @GetMapping("/adminIndex")
    public String adminHome(Model model) {
        String username = getLoggedInUserDetails().getUsername();
        model.addAttribute("username", username);
        return "admin_index";
    }

    @GetMapping("/userIndex")
    public String userHome(Model model) {
        String username = getLoggedInUserDetails().getUsername();
        model.addAttribute("username", username);
        return "user_index";
    }

    @GetMapping("/form")
    public String form() {
        return "form";
    }

    @GetMapping("/adminPage")
    public String loadAdminPage() {
        return "admin_dashboard";
    }

    @PostMapping("/addUser")
    public String addUser(@RequestParam String name, @RequestParam String password, @RequestParam String role) {
        UserDao user = new UserDao(name, password, role);
        userService.saveUser(user);
        return "redirect:/api/home/getUsers";
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
        return "redirect:/api/home/getUsers";

    }

    public UserDetails getLoggedInUserDetails(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.getPrincipal() instanceof UserDetails){
            return (UserDetails) authentication.getPrincipal();
        }
        return null;
    }
}
