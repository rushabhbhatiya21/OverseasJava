package com.example.restaurant.repository;

import com.example.restaurant.model.UserDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserDao, Long> {
    Optional<UserDao> findUserDaoByUserId(Long userId);
    Optional<UserDao> findUserDaoByEmail(String email);

    Page<UserDao> findAllByRole(UserDao.Role role, Pageable pageable);

    Optional<UserDao> findUserDaoByRestaurantName(String restaurantName);

    List<UserDao> findUserDaoByRole(UserDao.Role role);
}
