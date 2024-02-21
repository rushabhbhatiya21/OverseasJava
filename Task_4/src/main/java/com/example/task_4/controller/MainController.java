package com.example.task_4.controller;

import com.example.task_4.model.UserModel;
import com.example.task_4.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String hello(Model model) {
        model.addAttribute("username", "Rushabh");
        return "index";
    }

    @GetMapping("/form")
    public String form() {
        return "form";
    }



//    @GetMapping("/getUsers")
//    @ResponseBody
//    public List<UserModel> getUsers(Model model) {
//        return userService.getAllUsers();
//    }

//    @GetMapping("/showUsers")
//    public String showUsers() {
//        return "home";
//    }


    @GetMapping("/getUsers")
    public String getUsers(Model model) {
        List<UserModel> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "home";
    }

    @PostMapping("/addUser")
    public String addUser(@RequestParam String name, @RequestParam String department) {
        UserModel user = new UserModel(name, department);
        userService.saveUser(user);
        return "redirect:/getUsers";
    }

}
