package com.example.task_5_1.services;

import com.example.task_5_1.model.UserDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserDao, Long> {
    Optional<UserDao> findByUsername(String userName);
}
