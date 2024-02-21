package com.example.task_6.services;

import com.example.task_6.model.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final IUserRepository userRepository;

    public List<UserDao> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<UserDao> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public void saveUser(UserDao user) {
        userRepository.save(user);
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    public Optional<UserDao> findUserByName(String name) {
        return userRepository.findByName(name);
    }

    public UserDao.Address createAddress(String address) {
        String city = address.split(",")[0];
        String state = address.split(",")[0];
        String country = address.split(",")[0];
        return new UserDao.Address(city, state, country);
    }

    public UserDetails getLoggedInUserDetails(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.getPrincipal() instanceof UserDetails){
            return (UserDetails) authentication.getPrincipal();
        }
        return null;
    }
}
