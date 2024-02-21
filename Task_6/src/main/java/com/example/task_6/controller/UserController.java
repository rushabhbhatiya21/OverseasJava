package com.example.task_6.controller;

import com.example.task_6.model.UserDao;
import com.example.task_6.security.UserInfoDetails;
import com.example.task_6.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/userIndex")
    public String userHome(Model model) {
        String username = userService.getLoggedInUserDetails().getUsername();
        model.addAttribute("username", username);
        return "user_index";
    }

    @GetMapping("/getUserDetails")
    public String getUserDetails(Model model) {
        String name = userService.getLoggedInUserDetails().getUsername();
        if (name == null)
            System.out.println("name null");
        System.out.println(name);
        Optional<UserDao> loggedInUser = userService.findUserByName(name);

        if (loggedInUser.isPresent()) {
            System.out.println(loggedInUser.get().getName());
            model.addAttribute("user", loggedInUser);
            return "user_dashboard";
        }
        return "user_index";
    }

    @PostMapping("/editUser")
    public String editUser(
            @RequestParam String editId,
            @RequestParam String name,
            @RequestParam String phoneNumber,
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
            if (!Objects.equals(user.get().getPhoneNumber(), phoneNumber)) user.get().setPhoneNumber(phoneNumber);
            if (!Objects.equals(user.get().getAddress().toString(), address)) user.get().setAddress(newAddress);
            if (!Objects.equals(user.get().getSelfBio(), selfBio)) user.get().setSelfBio(selfBio);
            user.get().setPhoto(photo);
            userService.saveUser(user.get());
        }
        return "redirect:/api/user/getUserDetails";
    }

}
