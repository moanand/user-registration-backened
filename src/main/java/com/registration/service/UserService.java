package com.registration.service;

import com.registration.model.User;

import java.util.List;

public interface UserService {
    boolean saveUser(User user);
    List<User> getAllUsers();
    User getUserById(int userId);
    User updateUser(User user);
    boolean deleteUser(int userId);
}
