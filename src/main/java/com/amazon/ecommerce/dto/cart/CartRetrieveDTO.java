package com.amazon.ecommerce.dto.cart;

import java.util.List;

import com.amazon.ecommerce.models.CartItems;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartRetrieveDTO {
    private Long id;
    private float totalPrice;
    private List<CartItems> cartItems; 
}
