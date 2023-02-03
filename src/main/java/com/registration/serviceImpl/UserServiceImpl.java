package com.registration.serviceImpl;

import com.registration.entity.AddressEntity;
import com.registration.entity.UserEntity;
import com.registration.model.Address;
import com.registration.model.User;
import com.registration.repository.UserRepository;
import com.registration.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
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

            AddressEntity addressEntity = new AddressEntity();
            BeanUtils.copyProperties(user.getAddress(), addressEntity);
            userEntity.setAddress(addressEntity);
            log.info("User details {}", userEntity);

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
                Address address = new Address();
                BeanUtils.copyProperties(userEntity.getAddress(), address);
                user.setAddress(address);
                userList.add(user);
            });
            log.info("User List :: {}", userList);
            return userList;
        }
        return userList;
    }

    @Override
    public User getUserById(Integer userId) {
        Optional<UserEntity> optionalUserEntity = userRepository.findById(userId);
        User user = new User();
        if (optionalUserEntity.isPresent()) {
            UserEntity userEntity = optionalUserEntity.get();
            BeanUtils.copyProperties(userEntity, user);
            Address address = new Address();
            BeanUtils.copyProperties(userEntity.getAddress(), address);
            user.setAddress(address);
            return user;
        }
        return user;
    }

    @Override
    public boolean updateUser(User user, Integer userId) {
        Optional<UserEntity> userData = userRepository.findById(userId);
        if (userData.isPresent()) {
            user.setUid(userId);
            UserEntity userEntity = userData.get();
            BeanUtils.copyProperties(user, userEntity);
            userRepository.save(userEntity);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteUser(Integer userId) {
        Optional<UserEntity> userData = userRepository.findById(userId);
        if (userData.isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }
}
