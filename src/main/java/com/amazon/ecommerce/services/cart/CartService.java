package com.amazon.ecommerce.services.cart;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.amazon.ecommerce.dto.cart.CartRetrieveDTO;
import com.amazon.ecommerce.exceptions.ResourceNotFoundException;
import com.amazon.ecommerce.models.Cart;
import com.amazon.ecommerce.repository.CartRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartService implements ICartService {

    private final CartRepository cartRepository;
    private final ModelMapper modelMapper;

    @Override
    public CartRetrieveDTO getCart(Long id) {
        Cart cart =  cartRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Cart not found"));

        return modelMapper.map(cart, CartRetrieveDTO.class); 
    }

    

}
