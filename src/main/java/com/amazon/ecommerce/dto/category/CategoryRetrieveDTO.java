package com.amazon.ecommerce.dto.category;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRetrieveDTO {
    
    private Long id;
    
    @NotBlank(message = "Category name is required")
    private String name;
}
