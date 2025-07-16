package com.amazon.ecommerce.services.users;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.amazon.ecommerce.dto.user.UserCreateUpdateDto;
import com.amazon.ecommerce.exceptions.ResourceNotFoundException;
import com.amazon.ecommerce.models.User;
import com.amazon.ecommerce.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService{

    private final UserRepository userRepository;
    private final AuthenticationManager auth;
    private final JwtService jwtService;
    private final ModelMapper modelMapper;

    @Override
    public String verify(UserCreateUpdateDto userCreateDto) {
        var authentication = auth.authenticate(new UsernamePasswordAuthenticationToken(
            userCreateDto.getUsername(), userCreateDto.getPassword()));
            
        if (authentication.isAuthenticated()) 
            return jwtService.generateToken(userCreateDto.getUsername());
    
        return "Login failed";
    }

    @Override
    public User register(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> 
            new ResourceNotFoundException("user not found")
        );
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public User updateUser(UserCreateUpdateDto request, Long userId) {

        var oldUser = getUserById(userId);

        // var userObj = modelMapper.map(request, User.class);

        oldUser.setUsername(request.getUsername());
        oldUser.setEmail(request.getEmail());

        var user = userRepository.save(oldUser);

        return user;
    }


}
