package com.amazon.ecommerce.services.cart;

import java.util.List;

import com.amazon.ecommerce.dto.cart.CartRetrieveDTO;
import com.amazon.ecommerce.models.Cart;

public interface ICartService {
    
    CartRetrieveDTO getCart(Long id);
    // CartRetrieveDTO addItemsToCart(Long id );
}
