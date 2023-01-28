package com.registration.serviceImpl;

import com.registration.entity.UserEntity;
import com.registration.model.User;
import com.registration.repository.UserRepository;
import com.registration.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean saveUser(User user) {
        if (null != user) {
            UserEntity userEntity = new UserEntity();
            BeanUtils.copyProperties(user, userEntity);
            userRepository.save(userEntity);
            return true;
        }
        return false;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        List<UserEntity> usersEntityList = userRepository.findAll();
        if (!usersEntityList.isEmpty()) {
            usersEntityList.stream().forEach(userEntity -> {
                User user = new User();
                BeanUtils.copyProperties(userEntity, user);
                userList.add(user);
            });
            return userList;
        }
        return userList;
    }

    @Override
    public User getUserById(int userId) {
        Optional<UserEntity> optionalUserEntity = userRepository.findById(userId);
        User user = new User();
        if (optionalUserEntity.isPresent()) {
            UserEntity userEntity = optionalUserEntity.get();
            BeanUtils.copyProperties(userEntity, user);
            return user;
        }
        return user;
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public boolean deleteUser(int userId) {
        return false;
    }
}
