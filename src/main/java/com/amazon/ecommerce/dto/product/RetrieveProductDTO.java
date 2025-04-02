package com.amazon.ecommerce.dto.product;

import java.math.BigDecimal;
import java.util.List;

import com.amazon.ecommerce.dto.category.CategoryRetrieveDTO;
import com.amazon.ecommerce.models.Image;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class RetrieveProductDTO {
    private Long id;
    
    private String name;

    private String brand;

    private BigDecimal price;

    private String description;

    private int quantity;

    private CategoryRetrieveDTO category;

    private List<Image> image;
}
