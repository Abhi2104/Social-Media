package com.app.RegistrationLogin.service;

import com.app.RegistrationLogin.dto.LoginDTO;
import com.app.RegistrationLogin.dto.UserDTO;
import com.app.RegistrationLogin.entity.User;

public interface UserService {

	User addUser(UserDTO userDTO);

	String loginUser(LoginDTO loginDTO);

}
