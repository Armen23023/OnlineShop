package com.example.onlineshop.mappers;

import com.example.onlineshop.dto.response.ProductResponse;
import com.example.onlineshop.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;
@Component
@RequiredArgsConstructor
public class ProductResponseMapper implements Function<Product, ProductResponse> {
    @Override
    public ProductResponse apply(final Product product) {
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setPrice(product.getPrice());
        response.setCount(product.getCount());
        response.setCategory(product.getCategory());

        return response;
    }
}
