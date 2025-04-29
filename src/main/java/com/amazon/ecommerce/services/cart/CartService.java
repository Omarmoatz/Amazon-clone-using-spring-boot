package com.amazon.ecommerce.services.cart;

import java.util.List;

import org.springframework.stereotype.Service;

import com.amazon.ecommerce.models.Cart;
import com.amazon.ecommerce.repository.CartRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class CartService implements ICartService {

    private final CartRepository cartRepository; 

    @Override
    public List<Cart> getCart() {
        return cartRepository.findAll();
    }

}
