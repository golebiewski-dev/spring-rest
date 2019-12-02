package com.staxter.springrest.user.web;

import com.staxter.springrest.user.UserDto;
import com.staxter.springrest.user.UserException;
import com.staxter.springrest.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@RestController()
@RequestMapping("/userservice")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public UserDto register(@RequestBody UserDto userDto) {
        return userService.userRegister(userDto);
    }

    @PostMapping("/login")
    public UserDto login(@RequestBody UserDto userDto) {
        return userService.userLogin(userDto);
    }

    @ResponseStatus(code = HttpStatus.CONFLICT)
    @ExceptionHandler(UserException.class)
    public Map<String, String> handleException(UserException e, WebRequest request) {
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("code", e.getCode());
        responseBody.put("description", e.getMessage());
        return responseBody;
    }
}
