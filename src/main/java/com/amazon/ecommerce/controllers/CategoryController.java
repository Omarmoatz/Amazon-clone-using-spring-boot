package com.amazon.ecommerce.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amazon.ecommerce.exceptions.ResourceAlreadyExistedException;
import com.amazon.ecommerce.models.Category;
import com.amazon.ecommerce.responses.ApiResponse;
import com.amazon.ecommerce.services.categories.CategoryService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/")
    public ResponseEntity<ApiResponse> getAllCategories() {
        try {
            var ctg = categoryService.findAll();
            return ResponseEntity.ok(new ApiResponse("found", ctg));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("failed to get the categories", HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponse> addCategory(@RequestParam String name){
        try{
            var ctg = new Category(name); 
            var newCtg = categoryService.addCategory(ctg);
            return ResponseEntity.ok(new ApiResponse("added successfully", newCtg));

        }catch(ResourceAlreadyExistedException e){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }


}
