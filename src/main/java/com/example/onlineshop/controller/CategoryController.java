package com.example.onlineshop.controller;

import com.example.onlineshop.dto.request.CategoryRequest;
import com.example.onlineshop.dto.request.UserRegistrationRequest;
import com.example.onlineshop.dto.response.AuthenticationResponse;
import com.example.onlineshop.dto.response.CategoryResponse;
import com.example.onlineshop.service.CategoryService;
import com.example.onlineshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/category")
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CategoryResponse> add(@RequestBody final CategoryRequest request) {
        return ResponseEntity.ok(categoryService.add(request));

    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<List<CategoryResponse>> allCategories() {
        return ResponseEntity.ok(categoryService.allCategories());

    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity delete(@PathVariable(name = "id") long id) {
        categoryService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
