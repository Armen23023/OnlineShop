package com.example.onlineshop.service;

import com.example.onlineshop.dto.request.ProductRequest;
import com.example.onlineshop.dto.response.CategoryResponse;
import com.example.onlineshop.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {

    ProductResponse add(ProductRequest request);

    ProductResponse setCount(ProductRequest request);
    ProductResponse setPrice(ProductRequest request);

    void deleteById(Long productId);

    List<ProductResponse> allProducts();
}
