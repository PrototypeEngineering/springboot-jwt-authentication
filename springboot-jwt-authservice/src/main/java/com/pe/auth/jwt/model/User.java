package com.pe.auth.jwt.model;

import javax.persistence.Entity;

import org.springframework.data.annotation.Id;

@Entity
public class User {

	@Id
	private String userId;
	private String userPassword;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userPassword=" + userPassword + "]";
	}

}
