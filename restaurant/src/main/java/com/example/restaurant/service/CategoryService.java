package com.example.restaurant.service;

import com.example.restaurant.model.AreaDao;
import com.example.restaurant.model.CategoryDao;
import com.example.restaurant.model.SubCategoryDao;
import com.example.restaurant.repository.CategoryRepository;
import com.example.restaurant.repository.SubCategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subCategoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, SubCategoryRepository subCategoryRepository) {
        this.categoryRepository = categoryRepository;
        this.subCategoryRepository = subCategoryRepository;
    }


    public Page<CategoryDao> findAllCategoryPages(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return categoryRepository.findAll(pageable);
    }

    public Page<SubCategoryDao> findAllSubCategoryPages(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return subCategoryRepository.findAll(pageable);
    }

    public Optional<CategoryDao> findCategoryByName(String categoryName) {
        return categoryRepository.findCategoryDaoByCategoryName(categoryName);
    }

    public Optional<SubCategoryDao> findSubCategoryByName(String subCategoryName) {
        return subCategoryRepository.findSubCategoryDaoBySubCategoryName(subCategoryName);
    }

    public boolean existsCategoryByName(String categoryName) {
        return categoryRepository.existsCategoryDaoByCategoryName(categoryName);
    }

    public boolean existsSubCategoryByName(String subCategoryName) {
        return subCategoryRepository.existsSubCategoryDaoBySubCategoryName(subCategoryName);
    }

    public void saveCategory(CategoryDao category) {
        categoryRepository.save(category);
    }

    public void saveSubCategory(SubCategoryDao subCategory) {
        subCategoryRepository.save(subCategory);
    }

    public List<CategoryDao> findAllCategories() {
        return categoryRepository.findAll();
    }

    public List<SubCategoryDao> findAllSubCategories() { return subCategoryRepository.findAll(); }
    @Transactional
    public void deleteCategoryById(Long categoryId) {
        try {
            subCategoryRepository.deleteSubCategoryDaoByCategory_CategoryId(categoryId);
            categoryRepository.deleteById(categoryId);
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void deleteSubCategoryById(Long subCategoryId) {
        Optional<SubCategoryDao> subCategory = subCategoryRepository.findSubCategoryDaoBySubCategoryId(subCategoryId);
        subCategory.get().setCategory(null);
        subCategoryRepository.deleteById(subCategoryId);
    }
}
