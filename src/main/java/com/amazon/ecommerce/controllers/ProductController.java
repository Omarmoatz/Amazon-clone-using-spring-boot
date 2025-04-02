package com.amazon.ecommerce.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazon.ecommerce.dto.product.AddProductRequestDTO;
import com.amazon.ecommerce.responses.ApiResponse;
import com.amazon.ecommerce.services.products.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllProducts() {
        var products = productService.getALlProducts();
        return ResponseEntity.ok(new ApiResponse("found", products));
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addProduct(@RequestBody @Valid AddProductRequestDTO request) {
        var product = productService.addProduct(request);
        return ResponseEntity.ok(new ApiResponse("added product successfully", product));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getProductById(@PathVariable Long id) {
        var product = productService.getProductById(id);
        return ResponseEntity.ok(new ApiResponse("Found", product));
    }
}
