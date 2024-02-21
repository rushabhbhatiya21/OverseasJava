package com.example.overseas.repository;

import com.example.overseas.model.UserDao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserDao, Integer> {
//    List<UserDao> findAll();
    Optional<UserDao> findUserDaoByUsername(String username);

    Optional<UserDao> findUserDaoById(Integer id);
}
