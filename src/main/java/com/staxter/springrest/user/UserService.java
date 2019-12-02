package com.staxter.springrest.user;

public interface UserService {

    UserDto userRegister(UserDto userDto);

    UserDto userLogin(UserDto userDto);
}
