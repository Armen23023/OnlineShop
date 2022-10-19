package com.example.onlineshop.dto.response;

import com.example.onlineshop.model.ProductCategory;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private Long id;
    private String name;
    private Long price;
    private Long count;
    private ProductCategory category;
}
