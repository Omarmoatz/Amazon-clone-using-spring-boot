package com.amazon.ecommerce.services.users;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;

import javax.crypto.KeyGenerator;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    public String generateToken(String username) {
        var claims = new HashMap<String, Object>();
        return Jwts.builder()
                .addClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 60))
                .signWith(getKey())
                .compact();
    }

    private Key getKey(){
        try {
            var genKey = KeyGenerator.getInstance("hmacSHA256").generateKey();
            var secretKey = Base64.getEncoder().encodeToString(genKey.getEncoded());
            var keyBytes = Decoders.BASE64.decode(secretKey);
            return Keys.hmacShaKeyFor(keyBytes);

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException();
        }
    }

    public String extractUsername(String token) {
        return "";
    }

    public boolean validate(String token, UserDetails userDetail) {
        return true;
    }

}
