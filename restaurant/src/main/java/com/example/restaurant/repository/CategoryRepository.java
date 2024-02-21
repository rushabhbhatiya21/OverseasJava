package com.example.restaurant.repository;

import com.example.restaurant.model.CategoryDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryDao, Long> {
    boolean existsCategoryDaoByCategoryName(String categoryName);

    Optional<CategoryDao> findCategoryDaoByCategoryName(String categoryName);
}
