package com.example.onlineshop.service.impl;

import com.example.onlineshop.dto.request.ProductRequest;
import com.example.onlineshop.dto.response.ProductListResponse;
import com.example.onlineshop.dto.response.ProductResponse;
import com.example.onlineshop.exceptions.BadRequestException;
import com.example.onlineshop.exceptions.ResourceNotFoundException;
import com.example.onlineshop.mappers.ProductRequestMapper;
import com.example.onlineshop.mappers.ProductResponseMapper;
import com.example.onlineshop.model.Product;
import com.example.onlineshop.repository.ProductRepository;
import com.example.onlineshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductRequestMapper requestMapper;
    private final ProductResponseMapper productResponseMapper;

    @Override
    @Transactional
    public ProductResponse add(final ProductRequest request) {
        if (!productRepository.existsByName(request.getName())) {
            final Product product = productRepository.save(requestMapper.apply(request));
            log.info("Product: {} successfully added ", product);

            return productResponseMapper.apply(product);
        } else {
            throw new BadRequestException(String.format("Product with name = %s already exist", request.getName()));
        }
    }


    @Override
    @Transactional
    public void deleteById(Long productId) {
        productRepository.deleteById(productId);
    }


    @Override
    @Transactional(readOnly = true)
    public ProductListResponse allProducts(int page, int size) {
        Page<Product> all = productRepository.findAll(PageRequest.of(page, size));
        return new ProductListResponse(all.getContent()
                .stream()
                .map(productResponseMapper)
                .collect(Collectors.toList()), all.getTotalPages());
    }

    @Override
    @Transactional
    public ProductResponse update(final long productId, final ProductRequest productData) {
        final Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException(String.format("Product with id = %s not found", productId)));

        product.setCategory(productData.getCategory());
        product.setName(productData.getName());
        product.setPrice(productData.getPrice());
        product.setCount(productData.getCount());

        return productResponseMapper.apply(productRepository.save(product));
    }

    @Override
    public ProductListResponse allProductsByCategory(long categoryId, int page, int size) {
        Page<Product> all = productRepository.findByCategory_Id(categoryId, PageRequest.of(page, size));
        return new ProductListResponse(all.getContent()
                .stream()
                .map(productResponseMapper)
                .collect(Collectors.toList()), all.getTotalPages());
    }
}
