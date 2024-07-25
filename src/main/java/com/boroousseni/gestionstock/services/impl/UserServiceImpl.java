package com.boroousseni.gestionstock.services.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.boroousseni.gestionstock.dto.BaseInfoDto;
import com.boroousseni.gestionstock.dto.ChangePasswordDto;
import com.boroousseni.gestionstock.dto.UserDto;
import com.boroousseni.gestionstock.exceptions.EntityNotFoundException;
import com.boroousseni.gestionstock.exceptions.ErrorCode;
import com.boroousseni.gestionstock.exceptions.InvalidEntityException;
import com.boroousseni.gestionstock.exceptions.InvalidOperationException;
import com.boroousseni.gestionstock.models.User;
import com.boroousseni.gestionstock.repository.UserRepository;
import com.boroousseni.gestionstock.services.UserService;
import com.boroousseni.gestionstock.validators.UserValidator;

import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public UserDto save(UserDto dto) {
		// TODO Auto-generated method stub
		List<String> errors = UserValidator.validate(dto);
		if (!errors.isEmpty()) {
			log.error("User is not valid {}", dto);
			throw new InvalidEntityException("L'user n'est pas valide", ErrorCode.USER_NOT_VALID, errors);
		}

		BaseInfoDto baseInfoDto = dto.getBaseInfo();

		if (userAlreadyExists(baseInfoDto.getEmail())) {
			throw new InvalidEntityException("Un autre user avec le meme email existe deja",
					ErrorCode.USER_ALREADY_EXISTS,
					Collections.singletonList("Un autre user avec le meme email existe deja dans la BDD"));
		}

		dto.setPassword(passwordEncoder.encode(dto.getPassword()));

		return UserDto.fromEntity(userRepository.save(UserDto.toEntity(dto)));
	}

	private boolean userAlreadyExists(String email) {
		Optional<User> user = userRepository.findUserByEmail(email);
		return user.isPresent();
	}

	@Override
	public UserDto findById(Integer id) {
		if (id == null) {
			log.error("User ID is null");
			return null;
		}
		return userRepository.findById(id).map(UserDto::fromEntity).orElseThrow(() -> new EntityNotFoundException(
				"Aucun user avec l'ID = " + id + " n' ete trouve dans la BDD", ErrorCode.USER_NOT_FOUND));
	}

	@Override
	public List<UserDto> findAll() {
		// TODO Auto-generated method stub
		return userRepository.findAll().stream().map(UserDto::fromEntity).collect(Collectors.toList());
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		if (id == null) {
			log.error("User ID is null");
			return;
		}
		userRepository.deleteById(id);
	}

	@Override
	public UserDto findByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.findUserByEmail(email).map(UserDto::fromEntity).orElseThrow(
				() -> new EntityNotFoundException("Aucun user avec l'email = " + email + " n' ete trouve dans la BDD",
						ErrorCode.USER_NOT_FOUND));
	}

	@Override
	public UserDto changePassword(ChangePasswordDto dto) {
		// TODO Auto-generated method stub
		validate(dto);
		Optional<User> userOptional = userRepository.findById(dto.getId());
		if (userOptional.isEmpty()) {
			log.warn("Aucun user n'a ete trouve avec l'ID " + dto.getId());
			throw new EntityNotFoundException("Aucun user n'a ete trouve avec l'ID " + dto.getId(),
					ErrorCode.USER_NOT_FOUND);
		}

		User user = userOptional.get();
		user.setPassword(passwordEncoder.encode(dto.getPassword()));

		return UserDto.fromEntity(userRepository.save(user));
	}

	private void validate(ChangePasswordDto dto) {
		if (dto == null) {
			log.warn("Impossible de modifier le mot de passe avec un objet NULL");
			throw new InvalidOperationException(
					"Aucune information n'a ete fourni pour pouvoir changer le mot de passe",
					ErrorCode.USER_CHANGE_PASSWORD_OBJECT_NOT_VALID);
		}
		if (dto.getId() == null) {
			log.warn("Impossible de modifier le mot de passe avec un ID NULL");
			throw new InvalidOperationException("ID user null:: Impossible de modifier le mote de passe",
					ErrorCode.USER_CHANGE_PASSWORD_OBJECT_NOT_VALID);
		}
		if (StringUtils.isEmpty(dto.getPassword()) || StringUtils.isEmpty(dto.getConfirmedPassword())) {
			log.warn("Impossible de modifier le mot de passe avec un mot de passe NULL");
			throw new InvalidOperationException("Mot de passe user null:: Impossible de modifier le mote de passe",
					ErrorCode.USER_CHANGE_PASSWORD_OBJECT_NOT_VALID);
		}
		if (!dto.getPassword().equals(dto.getConfirmedPassword())) {
			log.warn("Impossible de modifier le mot de passe avec deux mots de passe different");
			throw new InvalidOperationException(
					"Mots de passe user non conformes:: Impossible de modifier le mote de passe",
					ErrorCode.USER_CHANGE_PASSWORD_OBJECT_NOT_VALID);
		}
	}

}
