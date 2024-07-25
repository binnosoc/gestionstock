
package com.boroousseni.gestionstock.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boroousseni.gestionstock.models.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
	
Optional<Company> findCompanyByName(String name);

}
