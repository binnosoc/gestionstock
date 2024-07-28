package com.boroousseni.gestionstock.services;

import java.util.List;

import com.boroousseni.gestionstock.dto.ChangePasswordDto;
import com.boroousseni.gestionstock.dto.UserDto;

public interface UserService {

	UserDto save(UserDto dto);

	UserDto findById(Integer id);

	List<UserDto> findAll();

	void delete(Integer id);

	UserDto findByEmail(String email);

	UserDto changePassword(ChangePasswordDto dto);
}
