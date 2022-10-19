package com.example.onlineshop.service.impl;

import com.example.onlineshop.dto.request.ProductRequest;
import com.example.onlineshop.dto.response.CategoryResponse;
import com.example.onlineshop.dto.response.ProductResponse;
import com.example.onlineshop.exceptions.BadRequestException;
import com.example.onlineshop.mappers.ProductAddRequestToProductMapper;
import com.example.onlineshop.mappers.ProductUpdateRequestToProductMapper;
import com.example.onlineshop.model.Product;
import com.example.onlineshop.model.ProductCategory;
import com.example.onlineshop.repository.ProductRepository;
import com.example.onlineshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    private ProductAddRequestToProductMapper prodAddReqMapper;
    private ProductUpdateRequestToProductMapper updateRequestToProductMapper;

    @Override
    @Transactional
    public ProductResponse add(final ProductRequest request) {
        if (!productRepository.existsByName(request.getName())) {
            Product product = prodAddReqMapper.apply(request);
            Product savedProduct = productRepository.save(product);
            log.info("Product: {} successfully added ", savedProduct);

            return ProductResponse.builder()
                    .id(savedProduct.getId())
                    .name(savedProduct.getName())
                    .price(savedProduct.getPrice())
                    .count(savedProduct.getCount())
                    .category(savedProduct.getCategory())
                    .build();
        } else {
            setCount(request);
        }
        return null;
    }

    @Override
    @Transactional
    public ProductResponse setCount(ProductRequest request) {
        Long sum = request.getCount() + productRepository.getByCount(request.getName());

        //nayel>>>>>>>>

        Product updatedProduct = productRepository.save(Product.builder()
                .name(request.getName())
                .price(request.getPrice())
                .count(sum)
                .category(request.getCategory())
                .build());
        log.info("Product: {} successfully updated ", updatedProduct);

        return ProductResponse.builder()
                .id(updatedProduct.getId())
                .name(updatedProduct.getName())
                .price(updatedProduct.getPrice())
                .count(updatedProduct.getCount())
                .build();
    }

    @Override
    @Transactional
    public ProductResponse setPrice(ProductRequest request) {
        if (productRepository.existsByName(request.getName())) {
            Product product = updateRequestToProductMapper.apply(request);
            Product savedProduct = productRepository.save(product);
            log.info("Product price: {} successfully changed ", savedProduct);

            return ProductResponse.builder()
                    .id(savedProduct.getId())
                    .name(savedProduct.getName())
                    .price(savedProduct.getPrice())
                    .count(savedProduct.getCount())
                    .build();
        } else {
            throw new BadRequestException(String.format("Product with name = %s not found", request.getName()));
        }
    }

    @Override
    @Transactional
    public void deleteById(Long productId) {
        productRepository.deleteById(productId);
    }




    @Override
    @Transactional(readOnly = true)
    public List<ProductResponse> allProducts() {
        return productRepository.findAll()
                .stream()
                .map(product -> new ProductResponse(product.getId(), product.getName(), product.getCount(), product.getPrice(),product.getCategory()))
                .collect(Collectors.toList());
    }
}
