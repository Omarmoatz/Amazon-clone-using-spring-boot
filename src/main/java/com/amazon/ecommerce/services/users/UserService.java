package com.amazon.ecommerce.services.users;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.amazon.ecommerce.dto.user.LoginDto;
import com.amazon.ecommerce.dto.user.UserCreateDto;
import com.amazon.ecommerce.dto.user.UserRetrieveDto;
import com.amazon.ecommerce.dto.user.UserUpdateDto;
import com.amazon.ecommerce.exceptions.ResourceNotFoundException;
import com.amazon.ecommerce.models.User;
import com.amazon.ecommerce.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final AuthenticationManager auth;
    private final JwtService jwtService;
    private final ModelMapper modelMapper;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @Override
    public Map<String, String> verify(LoginDto dto) {
        var authentication = auth.authenticate(new UsernamePasswordAuthenticationToken(
                dto.getUsername(), dto.getPassword()));
        
            
        if (!authentication.isAuthenticated())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "login Failed");
            
        Map<String, String> responseData = new HashMap<>();
        var token = jwtService.generateToken(dto.getUsername());
        responseData.put("token", token);
        return responseData;
    }

    @Override
    public UserRetrieveDto register(UserCreateDto request) {
        if (request.getConfirmPassword() != request.getPassword()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Passwords do not match"); 
        }
        var user = modelMapper.map(request, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return modelMapper.map(user, UserRetrieveDto.class);
    }

    @Override
    public List<UserRetrieveDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map((user) -> modelMapper.map(user, UserRetrieveDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found"));
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public User updateUser(UserUpdateDto request, Long userId) {

        var oldUser = getUserById(userId);

        // var userObj = modelMapper.map(request, User.class);

        oldUser.setUsername(request.getUsername());
        oldUser.setEmail(request.getEmail());

        var user = userRepository.save(oldUser);

        return user;
    }

}
