package com.amazon.ecommerce.reposotiry;

import java.util.Locale.Category;

import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryReposotiry extends JpaRepository<Category,Long>{
    
}
