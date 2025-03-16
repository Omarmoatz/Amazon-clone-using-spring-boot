package com.amazon.ecommerce.reposotiry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amazon.ecommerce.models.Product;


public interface ProductReposotiry extends JpaRepository<Product,Long>{

    List<Product> findByCategoryName(String category);

    List<Product> findByBrand(String brand);

    List<Product> findByCategoryAndBrand(String category, String brand);

    List<Product> findByName(String name);

    List<Product> findByNameAndBrand(String brand, String name);

    Long CountByBrandAndName(String brand, String name);
}
