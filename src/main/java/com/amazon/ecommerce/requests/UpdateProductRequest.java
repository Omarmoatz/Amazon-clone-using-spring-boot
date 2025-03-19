package com.amazon.ecommerce.requests;

import java.math.BigDecimal;

import com.amazon.ecommerce.models.Category;

import lombok.Data;



@Data
public class UpdateProductRequest {
    private String name;

    private String brand;

    private BigDecimal price;

    private String description;

    private int quantity;   

    private Category category;
}
