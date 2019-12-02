package com.staxter.springrest.user.impl;

import com.staxter.springrest.user.UserAlreadyExistsException;
import com.staxter.springrest.user.UserNotFoundException;

interface UserRepository {

    User createUser(User user) throws UserAlreadyExistsException;

    User findUser(User user) throws UserNotFoundException;

}
