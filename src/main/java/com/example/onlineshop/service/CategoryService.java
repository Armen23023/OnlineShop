package com.example.onlineshop.service;

import com.example.onlineshop.dto.request.CategoryRequest;
import com.example.onlineshop.dto.request.UserRegistrationRequest;
import com.example.onlineshop.dto.response.AuthenticationResponse;
import com.example.onlineshop.dto.response.CategoryResponse;

import java.util.List;

public interface CategoryService {

    CategoryResponse add(CategoryRequest request);

    void deleteById(Long categoryId);

    List<CategoryResponse> allCategories();

}
