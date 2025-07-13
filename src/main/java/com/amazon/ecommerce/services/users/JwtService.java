package com.amazon.ecommerce.services.users;

import org.springframework.stereotype.Service;

@Service
public class JwtService {
    public String generateToken(String username) {
        // Logic to generate JWT token using the username
        // This is a placeholder; actual implementation will depend on your JWT library
        return "token-for-" + username;
    }
}
