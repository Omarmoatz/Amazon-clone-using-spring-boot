package com.amazon.ecommerce.reposotiry;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amazon.ecommerce.models.Image;


public interface ImageReposotiry extends JpaRepository<Image,Long>{
    
}
