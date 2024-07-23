package com.boroousseni.gestionstock.repository;

import java.util.Optional;

import com.boroousseni.gestionstock.models.Company;

public interface CompanyRepository {
Optional<Company> findCompanyByName(String name);
}
