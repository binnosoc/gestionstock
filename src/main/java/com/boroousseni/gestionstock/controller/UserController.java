package com.boroousseni.gestionstock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.boroousseni.gestionstock.controller.api.UserApi;
import com.boroousseni.gestionstock.dto.ChangePasswordDto;
import com.boroousseni.gestionstock.dto.UserDto;
import com.boroousseni.gestionstock.services.UserService;

@RestController
public class UserController implements UserApi {

	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	@Override
	public UserDto save(UserDto dto) {
		// TODO Auto-generated method stub
		return userService.save(dto);
	}

	@Override
	public UserDto findById(Integer id) {
		// TODO Auto-generated method stub
		return userService.findById(id);
	}

	@Override
	public List<UserDto> findAllByCompanyId(Integer id) {
		// TODO Auto-generated method stub
		return userService.findAllByCompanyId(id);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		userService.delete(id);
	}

	@Override
	public UserDto findByEmail(String email) {
		// TODO Auto-generated method stub
		return userService.findByEmail(email);
	}

	@Override
	public UserDto changePassword(ChangePasswordDto dto) {
		// TODO Auto-generated method stub
		return userService.changePassword(dto);
	}

}
