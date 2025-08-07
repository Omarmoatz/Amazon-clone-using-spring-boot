package com.amazon.ecommerce.services.users;

import java.util.List;
import java.util.Map;

import com.amazon.ecommerce.dto.user.LoginDto;
import com.amazon.ecommerce.dto.user.UserCreateDto;
import com.amazon.ecommerce.dto.user.UserRetrieveDto;
import com.amazon.ecommerce.dto.user.UserUpdateDto;
import com.amazon.ecommerce.models.User;

public interface IUserService {

    Map<String, String> verify(LoginDto dto);

    List<UserRetrieveDto> getAllUsers();
    User getUserById(Long userId);
    UserRetrieveDto register(UserCreateDto user);
    User updateUser(UserUpdateDto request, Long userId);
    void deleteUser(Long userId);
}
