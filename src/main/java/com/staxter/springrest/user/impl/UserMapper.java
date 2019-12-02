package com.staxter.springrest.user.impl;

import com.staxter.springrest.user.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto mapUserToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setUserName(user.getUserName());
        return userDto;
    }


    public User mapUserDtoToUser(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setUserName(userDto.getUserName());
        user.setPlainTextPassword(userDto.getPassword());
        return user;
    }
}
