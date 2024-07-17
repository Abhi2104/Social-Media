package com.app.RegistrationLogin.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.RegistrationLogin.entity.User;

public interface UserRepo extends JpaRepository<User, Long> {
	
	User findByEmail(String email);
	
	
}
