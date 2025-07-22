package com.amazon.ecommerce.controllers;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazon.ecommerce.dto.user.UserCreateUpdateDto;
import com.amazon.ecommerce.models.User;
import com.amazon.ecommerce.responses.ApiResponse;
import com.amazon.ecommerce.services.users.JwtService;
import com.amazon.ecommerce.services.users.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    
    private final UserService userService;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    // @PostMapping("/login")
    // public ResponseEntity<?> login(@RequestBody @Valid UserCreateUpdateDto login) {
    //     System.out.println("-----------------in login ------------");
    //     var authToken = new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword());
    //     var auth = authenticationManager.authenticate(authToken);
    //     var jwt = jwtService.generateToken(auth.getName());
    //     return ResponseEntity.ok(jwt);
    // }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserCreateUpdateDto dto){
        var token = userService.verify(dto);
        return ResponseEntity.ok(new ApiResponse("success", token)); 
    }

    @PostMapping("/register")
    public User register(@RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userService.register(user);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }

}
