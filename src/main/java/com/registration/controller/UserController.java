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
            return new ResponseEntity<>(true, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/users")
    public ResponseEntity<UserResponse> findAllUsers() {
        List<User> users = userService.getAllUsers();
        System.out.println(users);
        UserResponse userResponse = new UserResponse();
        if (!users.isEmpty()) {
            userResponse.setUsers(users);
            userResponse.setMessage("All Records fetched successfully");
            return new ResponseEntity<>(userResponse, HttpStatus.OK);
        }
        userResponse.setMessage("Records not available in the database");
        return new ResponseEntity<>(userResponse, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/users/{id}")
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

    @PutMapping("/users/{id}")
    public ResponseEntity<Boolean> updateUser(@PathVariable Integer id,@RequestBody User user) {
        boolean isUserUpdated = userService.updateUser(user, id);
        if (isUserUpdated) {
            return new ResponseEntity<>(isUserUpdated, HttpStatus.OK);
        }
        return new ResponseEntity<>(isUserUpdated, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable Integer id) {
        boolean isUserDeleted = userService.deleteUser(id);
        if (isUserDeleted) {
            return new ResponseEntity<>(true, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
