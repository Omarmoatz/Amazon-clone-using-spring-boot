package com.amazon.ecommerce.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazon.ecommerce.dto.category.CategoryCreateDTO;
import com.amazon.ecommerce.dto.category.CategoryUpdateDTO;
import com.amazon.ecommerce.exceptions.ResourceAlreadyExistedException;
import com.amazon.ecommerce.exceptions.ResourceNotFoundException;
import com.amazon.ecommerce.responses.ApiResponse;
import com.amazon.ecommerce.services.categories.CategoryService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllCategories() {
        try {
            var ctg = categoryService.findAll();
            return ResponseEntity.ok(new ApiResponse("found", ctg));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getCategoryById(@PathVariable Long id) {
        try {
            var ctg = categoryService.findById(id);
            return ResponseEntity.ok(new ApiResponse("found", ctg));

        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("name/{name}")
    public ResponseEntity<ApiResponse> getCategoryByName(@PathVariable String name) {
        try {
            var ctg = categoryService.findByName(name);
            if (ctg == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse("no category found by name: " + name, null));
            return ResponseEntity.ok(new ApiResponse("Found", ctg));

        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addCategory(@RequestBody @Valid CategoryCreateDTO request) {
        try {
            var newCtg = categoryService.addCategory(request);
            return ResponseEntity.ok(new ApiResponse("added successfully", newCtg));

        } catch (ResourceAlreadyExistedException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateCategory(
            @PathVariable Long id, @RequestBody @Valid CategoryUpdateDTO request) {

        try {
            var ctg = categoryService.updateCategory(request, id);
            return ResponseEntity.ok(new ApiResponse("updated", ctg));

        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), request));
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long id){

        try{
            categoryService.deleteCategoryById(id);
            return ResponseEntity.ok(new ApiResponse("deleted successfully category with id : " + id, null));
        }catch(ResourceNotFoundException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new ApiResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }

}
