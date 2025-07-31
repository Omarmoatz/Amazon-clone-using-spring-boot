package com.amazon.ecommerce.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazon.ecommerce.dto.user.LoginDto;
import com.amazon.ecommerce.dto.user.UserCreateDto;
import com.amazon.ecommerce.dto.user.UserRetrieveDto;
import com.amazon.ecommerce.responses.ApiResponse;
import com.amazon.ecommerce.services.users.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    
    private final UserService userService;

    @GetMapping
    public ResponseEntity<?> getAllUsers(){
        return ResponseEntity.ok(new ApiResponse("success", userService.getAllUsers()));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginDto dto){
        var token = userService.verify(dto);
        return ResponseEntity.ok(new ApiResponse("success", token)); 
    }

    @PostMapping("/register")
    public ResponseEntity<UserRetrieveDto> register(@RequestBody @Valid UserCreateDto user){
        return new ResponseEntity<>(userService.register(user), HttpStatus.CREATED);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }

}
