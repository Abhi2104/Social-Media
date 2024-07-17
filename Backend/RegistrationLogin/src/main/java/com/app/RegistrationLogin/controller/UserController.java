package com.app.RegistrationLogin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.RegistrationLogin.dto.LoginDTO;
import com.app.RegistrationLogin.dto.UserDTO;
import com.app.RegistrationLogin.entity.User;
import com.app.RegistrationLogin.service.UserService;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/save")
	public ResponseEntity<User>saveUser(@RequestBody UserDTO userDTO) {
		User u = null;
		try {
			u = userService.addUser(userDTO);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@RequestBody LoginDTO loginDTO){
		String loginMessage = userService.loginUser(loginDTO);
		return ResponseEntity.ok(loginMessage);
	} 
	
}
