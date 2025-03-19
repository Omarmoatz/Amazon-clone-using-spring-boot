package com.amazon.ecommerce.repository;

import com.amazon.ecommerce.models.Category;

import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepository extends JpaRepository<Category,Long>{

    Category findByName(String title);
}
