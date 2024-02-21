package com.example.restaurant.repository;

import com.example.restaurant.model.ProductDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductDao, Long> {
    boolean existsProductDaoByProductName(String productName);

    Page<ProductDao> findAllByRestaurant_UserId(Long userId, Pageable pageable);
}
