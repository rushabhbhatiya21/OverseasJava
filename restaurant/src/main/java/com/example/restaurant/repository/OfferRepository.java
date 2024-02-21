package com.example.restaurant.repository;

import com.example.restaurant.model.OfferDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepository extends JpaRepository<OfferDao, Long> {
    Page<OfferDao> findAllByRestaurant_UserId(Long userId, Pageable pageable);

    boolean existsOfferDaoByOfferName(String offerName);
}
