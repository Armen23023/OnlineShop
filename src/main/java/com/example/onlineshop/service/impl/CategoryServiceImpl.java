package com.example.onlineshop.service.impl;

import com.example.onlineshop.dto.request.CategoryRequest;
import com.example.onlineshop.dto.response.CategoryResponse;
import com.example.onlineshop.exceptions.BadRequestException;
import com.example.onlineshop.model.ProductCategory;
import com.example.onlineshop.repository.ProductCategoryRepository;
import com.example.onlineshop.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final ProductCategoryRepository categoryRepository;

    @Override
    @Transactional
    public CategoryResponse add(final CategoryRequest request) {
        if (!categoryRepository.existsByName(request.getName())) {
            ProductCategory productCategory = categoryRepository.save(ProductCategory.builder()
                    .name(request.getName())
                    .build());
            return new CategoryResponse(productCategory.getId(), productCategory.getName());
        } else {
            throw new BadRequestException(String.format("Category with name = %s already exist", request.getName()));
        }
    }

    @Override
    @Transactional
    public void deleteById(final Long categoryId) {
        categoryRepository.deleteById(categoryId);

    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryResponse> allCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(category -> new CategoryResponse(category.getId(), category.getName()))
                .collect(Collectors.toList());
    }
}
