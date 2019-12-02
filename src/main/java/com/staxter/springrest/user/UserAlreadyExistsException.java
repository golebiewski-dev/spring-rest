package com.staxter.springrest.user;

import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody()
public class UserAlreadyExistsException extends UserException {

    public UserAlreadyExistsException() {
        super("USER_ALREADY_EXISTS", "A user with the given username already exists");
    }
}
