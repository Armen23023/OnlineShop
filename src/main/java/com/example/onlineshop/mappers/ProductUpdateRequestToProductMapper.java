package com.example.onlineshop.mappers;

import com.example.onlineshop.dto.request.ProductRequest;
import com.example.onlineshop.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;
@Component
@RequiredArgsConstructor
public class ProductUpdateRequestToProductMapper implements Function<ProductRequest, Product> {
    @Override
    public Product apply(final ProductRequest request) {
        Product product = new Product();
        product.setPrice(request.getPrice());

        return product;
    }
}
