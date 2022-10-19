package com.example.onlineshop.mappers;

import com.example.onlineshop.dto.request.ProductRequest;
import com.example.onlineshop.dto.request.UserRegistrationRequest;
import com.example.onlineshop.model.Product;
import com.example.onlineshop.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.function.Function;


@Component
@RequiredArgsConstructor
public class ProductAddRequestToProductMapper implements Function<ProductRequest, Product> {


    @Override
    public Product apply(final ProductRequest request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setCount(request.getCount());
        product.setPrice(request.getPrice());
        product.setCategory(request.getCategory());

        return product;

    }

}
