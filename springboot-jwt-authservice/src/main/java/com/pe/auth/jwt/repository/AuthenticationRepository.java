package com.pe.auth.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pe.auth.jwt.model.User;

@Repository
public interface AuthenticationRepository extends JpaRepository<User, String> {
	
	 User findByUserIdAndUserPassword(String userId, String userPassword);

}
