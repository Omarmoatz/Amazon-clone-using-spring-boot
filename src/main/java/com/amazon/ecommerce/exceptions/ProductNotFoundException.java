package com.amazon.ecommerce.exceptions;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(String messege){
        super(messege);
    }
}
