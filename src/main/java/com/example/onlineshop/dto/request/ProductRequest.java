package com.example.onlineshop.dto.request;

import com.example.onlineshop.model.ProductCategory;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {
    private String name;
    private Long price;
    private Long count;
    private ProductCategory category;
}
