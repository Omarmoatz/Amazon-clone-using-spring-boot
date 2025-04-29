package com.amazon.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amazon.ecommerce.models.Cart;


public interface CartRepository extends JpaRepository<Cart,Long> {
    
}
