package com.example.onlineshop.repository;

import com.example.onlineshop.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Long> {

    boolean existsByName(String name);
}
