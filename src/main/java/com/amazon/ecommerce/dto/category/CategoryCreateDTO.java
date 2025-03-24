package com.amazon.ecommerce.dto.category;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class CategoryCreateDTO {
    
    @NotBlank(message = "Category name is required")
    private String name;
}