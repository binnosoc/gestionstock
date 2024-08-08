package com.boroousseni.gestionstock.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boroousseni.gestionstock.dto.CompanyDto;

import com.boroousseni.gestionstock.exceptions.EntityNotFoundException;
import com.boroousseni.gestionstock.exceptions.ErrorCode;
 import com.boroousseni.gestionstock.repository.CompanyRepository;
import com.boroousseni.gestionstock.repository.RoleRepository;
import com.boroousseni.gestionstock.services.CompanyService;

  
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Transactional(rollbackOn = Exception.class)
@Service
@Slf4j
public class CompanyServiceImpl implements CompanyService {

	private CompanyRepository companyRepository;
	
	private RoleRepository roleRepository;

	@Autowired
	public CompanyServiceImpl(CompanyRepository companyRepository ,
			RoleRepository roleRepository) {
		this.companyRepository = companyRepository;
	
		this.roleRepository = roleRepository;
	}



	private String generateRandomPassword() {
		return "som3R@nd0mP@$$word";
	}

	@Override
	public CompanyDto save(CompanyDto dto) {
		// TODO Auto-generated method stub
//		List<String> errors = CompanyValidator.validate(dto);
//		if (!errors.isEmpty()) {
//			log.error("Company is not valid {}", dto);
//			throw new InvalidEntityException("L'company n'est pas valide", ErrorCode.COMPANY_NOT_VALID, errors);
//		}
		CompanyDto savedCompany = CompanyDto.fromEntity(companyRepository.save(CompanyDto.toEntity(dto)));
//
//		UserDto user = fromCompany(savedCompany);
//
//		UserDto savedUser = userService.save(user);
//
//		RoleDto roleDto = RoleDto.builder().roleName("ADMIN").user(savedUser).build();
//
//		roleRepository.save(RoleDto.toEntity(roleDto));
//
//		return savedCompany;
		return savedCompany;
	}

	@Override
	public CompanyDto findById(Integer id) {
		// TODO Auto-generated method stub
		if (id == null) {
			log.error("Company ID is null");
			return null;
		}
		return companyRepository.findById(id).map(CompanyDto::fromEntity)
				.orElseThrow(() -> new EntityNotFoundException(
						"Aucune company avec l'ID = " + id + " n' ete trouve dans la BDD",
						ErrorCode.COMPANY_NOT_FOUND));
	}

	@Override
	public List<CompanyDto> findAll() {
		// TODO Auto-generated method stub
		return companyRepository.findAll().stream().map(CompanyDto::fromEntity).collect(Collectors.toList());
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		if (id == null) {
			log.error("Company ID is null");
			return;
		}
		companyRepository.deleteById(id);
	}

}
