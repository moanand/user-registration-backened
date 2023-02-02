package com.registration.controller;

import com.registration.model.User;
import com.registration.model.UserResponse;
import com.registration.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/userReg")
@CrossOrigin("*")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/addUser")
    public ResponseEntity<Boolean> save(@Valid @RequestBody User user) {
        boolean isSaveUser = userService.saveUser(user);
        UserResponse userResponse = new UserResponse();
        if (isSaveUser) {
            userResponse.setUser(user);
            userResponse.setMessage("User data saved successfully!");
            return new ResponseEntity<>(isSaveUser, HttpStatus.CREATED);
        }
        userResponse.setMessage("User creation was failed...");
        return new ResponseEntity<>(isSaveUser, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/users")
    public ResponseEntity<UserResponse> findAllUsers() {
        List<User> users = userService.getAllUsers();
        UserResponse userResponse = new UserResponse();
        if (!users.isEmpty()) {
            userResponse.setUsers(users);
            userResponse.setMessage("All Records fetched successfully");
            return new ResponseEntity<>(userResponse, HttpStatus.OK);
        }
        userResponse.setMessage("Records not available in the database");
        return new ResponseEntity<>(userResponse, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserResponse> findUserById(@PathVariable(name = "id") int userId) {
        User user = userService.getUserById(userId);
        UserResponse userResponse = new UserResponse();
        if (user != null) {
            userResponse.setUser(user);
            userResponse.setMessage("User with id " + userId + " is found");
            return new ResponseEntity<>(userResponse, HttpStatus.OK);
        }
        userResponse.setMessage("User not found with id " + userId);
        return new ResponseEntity<>(userResponse, HttpStatus.NOT_FOUND);
    }
}
