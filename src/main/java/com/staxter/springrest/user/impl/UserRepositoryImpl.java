package com.staxter.springrest.user.impl;

import com.staxter.springrest.user.UserAlreadyExistsException;
import com.staxter.springrest.user.UserNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
class UserRepositoryImpl implements UserRepository {

    private final HashMap<String, User> users;

    public UserRepositoryImpl() {
        users = new HashMap<>();
    }

    @Override
    public User createUser(User user) throws UserAlreadyExistsException {
        if (users.containsKey(user.getUserName())) {
            throw new UserAlreadyExistsException();
        }
        users.put(user.getUserName(), user);
        return user;
    }

    @Override
    public User findUser(User user) throws UserNotFoundException {
        if (!users.containsKey(user.getUserName())) {
            throw new UserNotFoundException();
        }
        return users.get(user.getUserName());
    }

}
