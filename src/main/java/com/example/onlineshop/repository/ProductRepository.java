package com.example.onlineshop.repository;

import com.example.onlineshop.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {

    boolean existsByName(String name);


    Page<Product> findByCategory_Id(long categoryId, Pageable page);

}
