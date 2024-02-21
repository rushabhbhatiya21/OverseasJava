package com.example.restaurant.controller;

import com.example.restaurant.model.AreaDao;
import com.example.restaurant.model.CityDao;
import com.example.restaurant.model.UserDao;
import com.example.restaurant.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/api/register")
public class RegisterController {

    private final AdminService adminService;

    @Autowired
    public RegisterController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/registerUser")
    public String registerUser(
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String restaurantName,
            @RequestParam String contactNumber,
            @RequestParam String address,
            @RequestParam String area,
            @RequestParam String city
    ) {
        //if user already exists
        if (adminService.userExists(email)) {
            Optional<UserDao> user = adminService.findUserByEmail(email);
            //if user exists and entered email & password matches
            if (password.equals(user.get().getPassword())) {
                //user already exists error
                return "redirect:/api/loginPage";
            }
            else {
                //throw error that username already exists
                return null;
            }
        }
        else {
            //create new user
            UserDao user = new UserDao();
            user.setEmail(email);
            user.setPassword(password);

            user.setRestaurantName(restaurantName);
            user.setContactNumber(contactNumber);
            user.setAddress(address);

            Optional<AreaDao> areaObj = adminService.findAreaByAreaName(area);
            areaObj.ifPresent(user::setArea);

            Optional<CityDao> cityObj = adminService.findCityByCityName(city);
            cityObj.ifPresent(user::setCity);

            user.setRole(UserDao.Role.USER);

            adminService.saveUser(user);
        }

        return "redirect:/api/loginPage";
    }

    @PostMapping("/registerAdmin")
    public String registerAdmin(
            @RequestParam String email,
            @RequestParam String password
    ) {
        //admin does not exist with that email
        if (adminService.findUserByEmail(email).isEmpty()) {
            UserDao admin = new UserDao();
            admin.setEmail(email);
            admin.setPassword(password);
            admin.setRole(UserDao.Role.ADMIN);
            adminService.saveUser(admin);

            return "redirect:/api/loginPage";
        }
        //admin already exist with that email
        return null;
    }
}
