package com.example.restaurant.service;

import com.example.restaurant.model.ProductDao;
import com.example.restaurant.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<ProductDao> findAllProductPages(Long userId, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return productRepository.findAllByRestaurant_UserId(userId, pageable);
    }

    public boolean existsProductByName(String productName) {
        return productRepository.existsProductDaoByProductName(productName);
    }

    public void saveProduct(ProductDao newProduct) {
        productRepository.save(newProduct);
    }

    public void deleteProduct(Long productId) {
        Optional<ProductDao> product = productRepository.findById(productId);
        product.get().setCategory(null);
        product.get().setSubCategory(null);
        product.get().setRestaurant(null);
        productRepository.deleteById(productId);
    }
}
