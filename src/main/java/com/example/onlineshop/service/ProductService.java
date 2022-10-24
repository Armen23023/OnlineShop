package com.example.onlineshop.service;

import com.example.onlineshop.dto.request.ProductRequest;
import com.example.onlineshop.dto.response.ProductListResponse;
import com.example.onlineshop.dto.response.ProductResponse;

public interface ProductService {

    ProductResponse add(ProductRequest request);

    void deleteById(Long productId);

    ProductListResponse allProducts(int page, int size);

    ProductResponse getProductById(Long productId);

    ProductResponse update(long productId, ProductRequest productDat);

    ProductListResponse allProductsByCategory(long categoryId, int page, int size);
}
