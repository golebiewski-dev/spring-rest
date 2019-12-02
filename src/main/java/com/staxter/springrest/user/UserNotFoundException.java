package com.staxter.springrest.user;

import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody()
public class UserNotFoundException extends UserException {

    public UserNotFoundException() {
        super("USER_NOT_FOUND", "User not found or incorrect password");
    }
}
