package com.example.restaurant.repository;

import com.example.restaurant.model.SubCategoryDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategoryDao, Long> {
    void deleteSubCategoryDaoByCategory_CategoryId(Long categoryId);

    boolean existsSubCategoryDaoBySubCategoryName(String subCategoryName);

    Optional<SubCategoryDao> findSubCategoryDaoBySubCategoryId(Long subCategoryId);

    Optional<SubCategoryDao> findSubCategoryDaoBySubCategoryName(String subCategoryName);
}
