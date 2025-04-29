package com.amazon.ecommerce.services.cart;

import java.util.List;

import com.amazon.ecommerce.models.Cart;

public interface ICartService {
    
    List<Cart> getCart();
}
