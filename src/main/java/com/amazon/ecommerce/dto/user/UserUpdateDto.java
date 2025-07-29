package com.amazon.ecommerce.dto.user;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDto {
    private String username;

    @Email(message = "invalid email format")
    private String email;

    private String password;
    
}
