package com.pe.auth.jwt.service;

import com.pe.auth.jwt.exception.UserAlreadyExistsException;
import com.pe.auth.jwt.exception.UserNotFoundException;
import com.pe.auth.jwt.model.User;

public interface AuthService {

	public User findByUserIdAndPassword(String username, String password) throws UserNotFoundException;

	boolean save(User user) throws UserAlreadyExistsException;
}
