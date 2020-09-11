package com.pe.auth.jwt.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pe.auth.jwt.exception.UserAlreadyExistsException;
import com.pe.auth.jwt.exception.UserNotFoundException;
import com.pe.auth.jwt.model.User;
import com.pe.auth.jwt.service.AuthService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

	@Autowired
	private AuthService authService;

	private Map<String, String> tokenMap = new HashMap<String, String>();

	@PostMapping("/saveuser")
	public ResponseEntity<?> save(@RequestBody final User user) {
		try {
			this.authService.save(user);
			return new ResponseEntity<String>("Created", HttpStatus.CREATED);
		} catch (UserAlreadyExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User user) {
		try {
			authService.findByUserIdAndPassword(user.getUserId(), user.getUserPassword());
			String token = getToken(user.getUserId(), user.getUserPassword());
			tokenMap.put("Message", "Login Successful");
			tokenMap.put("token", token);
			return new ResponseEntity<>(tokenMap, HttpStatus.OK);
		} catch (UserNotFoundException e) {
			tokenMap.put("Message", e.getMessage());
			tokenMap.put("token", null);
			return new ResponseEntity<>(tokenMap, HttpStatus.UNAUTHORIZED);
		} catch (Exception e) {
			tokenMap.put("Message", e.getMessage());
			tokenMap.put("token", null);
			return new ResponseEntity<>(tokenMap, HttpStatus.UNAUTHORIZED);
		}
	}

// Generate JWT token
	public String getToken(String username, String password) throws Exception {

		return Jwts.builder().setId(username).setSubject(password).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 500000))
				.signWith(SignatureAlgorithm.HS256, "uniquekey").compact();

	}

}
