package com.pe.auth.jwt.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pe.auth.jwt.exception.UserAlreadyExistsException;
import com.pe.auth.jwt.exception.UserNotFoundException;
import com.pe.auth.jwt.model.User;
import com.pe.auth.jwt.repository.AuthenticationRepository;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private AuthenticationRepository repository;

	@Override
	public User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException {
		User user = this.repository.findByUserIdAndUserPassword(userId, password);
		if (user == null) {
			throw new UserNotFoundException("User Not Found");
		}

		return user;
	}

	/*
	 * This method should be used to save a new user.Call the corresponding method
	 * of Respository interface.
	 */

	@Override
	public boolean save(User user) throws UserAlreadyExistsException {
		Optional<User> optional = this.repository.findById(user.getUserId());
		if (optional.isPresent()) {
			throw new UserAlreadyExistsException("ALREADY EXIST!");

		}
		repository.save(user);
		return Boolean.TRUE;
	}

}
