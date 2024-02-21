package com.example.task_6.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("api/auth")
public class AuthenticationController {


    @GetMapping("/login")
    public String login() {
        return "login_page";
    }
}
