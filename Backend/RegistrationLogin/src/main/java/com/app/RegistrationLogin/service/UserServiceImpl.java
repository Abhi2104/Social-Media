package com.app.RegistrationLogin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.RegistrationLogin.dto.LoginDTO;
import com.app.RegistrationLogin.dto.UserDTO;
import com.app.RegistrationLogin.entity.User;
import com.app.RegistrationLogin.repo.UserRepo;
@Service
public class UserServiceImpl implements UserService {

	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User addUser(UserDTO userDTO) {
				
		User user = new User(
				userDTO.getUserId(),
				userDTO.getFirstName(),
				userDTO.getLastName(),
				userDTO.getEmail(),
				passwordEncoder.encode(userDTO.getPassword())
				);
		
		userRepo.save(user);
		return user;
	}

	@Override
	public String loginUser(LoginDTO loginDTO) {
		User user1 = userRepo.findByEmail(loginDTO.getEmail());
		if(user1 != null) {
			String password = loginDTO.getPassword();
			String encodedPassword = user1.getPassword();
			Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
			if(isPwdRight) {
			 return "Login Successfull";
			}
			else
			{
			 return "Login failed Worng Password"; 
			}
		}
		else
		{
			return "Login failed Email not exist";
		}	
	}

}
