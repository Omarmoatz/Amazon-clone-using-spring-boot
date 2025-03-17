package com.amazon.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amazon.ecommerce.models.Image;


public interface ImageRepository extends JpaRepository<Image,Long>{
    
}
