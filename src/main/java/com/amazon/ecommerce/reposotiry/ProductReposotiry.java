package com.amazon.ecommerce.reposotiry;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amazon.ecommerce.models.Product;


public interface ProductReposotiry extends JpaRepository<Product,Long>{
    
}
