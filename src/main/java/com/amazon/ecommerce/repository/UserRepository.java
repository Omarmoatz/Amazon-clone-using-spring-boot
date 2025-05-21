package com.amazon.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amazon.ecommerce.models.User;


public interface UserRepository extends JpaRepository<User, Long>{
    User findUserByUsername(String username);   
}
