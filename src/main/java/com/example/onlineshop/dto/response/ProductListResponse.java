package com.example.onlineshop.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ProductListResponse {

    private List<ProductResponse> content;
    private int totalPage;
}
