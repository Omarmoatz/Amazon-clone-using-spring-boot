package com.amazon.ecommerce.services.users;

import java.util.List;

import com.amazon.ecommerce.dto.user.UserCreateDto;
import com.amazon.ecommerce.dto.user.UserRetrieveDto;
import com.amazon.ecommerce.dto.user.UserUpdateDto;
import com.amazon.ecommerce.models.User;

public interface IUserService {

    String verify(UserCreateDto userCreateDto);

    List<UserRetrieveDto> getAllUsers();
    User getUserById(Long userId);
    UserRetrieveDto register(UserCreateDto user);
    User updateUser(UserUpdateDto request, Long userId);
    void deleteUser(Long userId);
}
