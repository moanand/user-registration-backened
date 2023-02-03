package com.registration.service;

import com.registration.model.User;

import java.util.List;

public interface UserService {
    boolean saveUser(User user);

    List<User> getAllUsers();

    User getUserById(Integer userId);

    boolean updateUser(User user, Integer userId);

    boolean deleteUser(Integer userId);
}
