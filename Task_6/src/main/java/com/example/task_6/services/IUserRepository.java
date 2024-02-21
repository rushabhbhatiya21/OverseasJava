package com.example.task_6.services;


import com.example.task_6.model.UserDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserDao, Long> {
    Optional<UserDao> findByName(String userName);
}
