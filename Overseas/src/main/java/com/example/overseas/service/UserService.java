package com.example.overseas.service;

import com.example.overseas.model.UserDao;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;

@Service
public class UserService {
    public UserDetails getLoggedInUserDetails(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.getPrincipal() instanceof UserDetails){
            return (UserDetails) authentication.getPrincipal();
        }
        return null;
    }

    public String getOriginalUrl(UserDao user) {
        UserDao.Role role = user.getRole();
        // Get the original URL from the request
        String originalUrl = "";
        if (role.equals(UserDao.Role.STUDENT)) {
            originalUrl = "http://localhost:8080/student/studentIndex";
        } else if (role.equals(UserDao.Role.CONSULTANT)) {
            originalUrl = "http://localhost:8080/consultant/consultantIndex";
        }
        else {
            originalUrl = "http://localhost:8080/logout";
        }
        return originalUrl;
    }
}
