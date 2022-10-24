package com.example.onlineshop.controller;


import com.example.onlineshop.dto.request.ProductRequest;
import com.example.onlineshop.dto.response.ProductListResponse;
import com.example.onlineshop.dto.response.ProductResponse;
import com.example.onlineshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ProductResponse> add(@RequestBody final ProductRequest request) {
        return ResponseEntity.ok(productService.add(request));
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ProductResponse> update(@PathVariable("id") final long productId,
                                                  @RequestBody final ProductRequest productDat) {
        return ResponseEntity.ok(productService.update(productId, productDat));
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity delete(@PathVariable(name = "id") final long id) {
        productService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity getProduct(@PathVariable(name = "id") final long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<ProductListResponse> allProducts(@RequestParam(name = "page", required = false, defaultValue = "0") final int page,
                                                           @RequestParam(name = "size", required = false, defaultValue = "10") final int size) {
        return ResponseEntity.ok(productService.allProducts(page, size));
    }

    @GetMapping("/category/{categoryId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<ProductListResponse> allProducts(@PathVariable(name = "categoryId") long categoryId,
                                                           @RequestParam(name = "page", required = false, defaultValue = "0") final int page,
                                                           @RequestParam(name = "size", required = false, defaultValue = "10") final int size) {
        return ResponseEntity.ok(productService.allProductsByCategory(categoryId, page, size));
    }
}
