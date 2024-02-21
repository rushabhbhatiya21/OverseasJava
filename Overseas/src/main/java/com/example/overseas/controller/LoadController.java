package com.example.overseas.controller;

import com.example.overseas.model.UserDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class LoadController {

    @GetMapping
    public String redirectToRoleSelect() {
        return "redirect:/roleSelect";
    }

    @GetMapping("/roleSelect")
    public String loadRoleSelect() {
        return "roleSelect";
    }

    @GetMapping("/login")
    public String loadLoginPage() {
        return "loginPage";
    }

    @GetMapping("/auth/loadChangePasswordForm")
    public String loadChangePassForm(Model model, String role) {
        model.addAttribute("role", role);
        return "changePassword";
    }

    @PostMapping("/loadRegistrationForm")
    public String loadRegistrationForm(@RequestParam String role) {
        if (role.equals(UserDao.Role.STUDENT.name())) {
            return "createStudent";
        } else if (role.equals(UserDao.Role.CONSULTANT.name())) {
            return "createConsultant";
        }
        else
            return null;
    }


}
