package com.amazon.ecommerce.services.users;

import java.util.List;

import com.amazon.ecommerce.dto.user.UserCreateUpdateDto;
import com.amazon.ecommerce.models.User;

public interface IUserService {

    String verify(UserCreateUpdateDto userCreateDto);

    List<User> getAllUsers();
    User getUserById(Long userId);
    User register(User user);
    User updateUser(UserCreateUpdateDto request, Long userId);
    void deleteUser(Long userId);
}
