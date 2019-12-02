package com.staxter.springrest.user.impl;

import com.staxter.springrest.user.UserDto;
import com.staxter.springrest.user.UserNotFoundException;
import com.staxter.springrest.user.UserService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
class UserServiceImpl implements UserService {

    UserRepository userRepository;
    UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDto userRegister(UserDto userDto) {
        User user = userMapper.mapUserDtoToUser(userDto);
        user.setId(UUID.randomUUID().toString());
        user.setHashedPassword(BCrypt.hashpw(user.getPlainTextPassword(), BCrypt.gensalt()));
        return userMapper.mapUserToUserDto(userRepository.createUser(user));
    }

    public UserDto userLogin(UserDto userDto) {
        User user = userRepository.findUser(userMapper.mapUserDtoToUser(userDto));
        if (!BCrypt.checkpw(userDto.getPassword(), user.getHashedPassword())) {
            throw new UserNotFoundException();
        }
        return userMapper.mapUserToUserDto(user);
    }

}
