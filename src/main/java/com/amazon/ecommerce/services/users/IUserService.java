package com.amazon.ecommerce.services.users;

import java.util.List;

import com.amazon.ecommerce.models.User;

public interface IUserService {

    List<User> getAllUsers();
    User register(User user);

    void deleteUser(Long userId);
}
