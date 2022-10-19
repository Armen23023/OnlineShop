package com.example.onlineshop.controller;


import com.example.onlineshop.dto.request.CategoryRequest;
import com.example.onlineshop.dto.request.ProductRequest;
import com.example.onlineshop.dto.response.CategoryResponse;
import com.example.onlineshop.dto.response.ProductResponse;
import com.example.onlineshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ProductResponse> add(@RequestBody final ProductRequest request) {
        return ResponseEntity.ok(productService.add(request));
    }


    @PostMapping("/setprice")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ProductResponse> setPrice(@RequestBody final ProductRequest request){
        return ResponseEntity.ok(productService.setPrice(request));
    }



    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity  delete(@PathVariable(name = "id")long id){
        productService.deleteById(id);
        return ResponseEntity.ok().build();    }


    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<List<ProductResponse>> allProducts() {
        return ResponseEntity.ok(productService.allProducts());

    }
}
