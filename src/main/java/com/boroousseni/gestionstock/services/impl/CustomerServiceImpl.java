package com.boroousseni.gestionstock.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boroousseni.gestionstock.dto.CustomerDto;
import com.boroousseni.gestionstock.exceptions.EntityNotFoundException;
import com.boroousseni.gestionstock.exceptions.ErrorCode;
import com.boroousseni.gestionstock.exceptions.InvalidEntityException;
import com.boroousseni.gestionstock.models.Customer;
import com.boroousseni.gestionstock.repository.CustomerRepository;
import com.boroousseni.gestionstock.services.CustomerService;
import com.boroousseni.gestionstock.validators.CustomerValidator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerDto save(CustomerDto dto) {
        // Validation de l'entité Customer
        List<String> errors = CustomerValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("Customer is not valid {}", dto);
            throw new InvalidEntityException("Le client n'est pas valide", ErrorCode.CUSTOMER_NOT_VALID, errors);
        }

        // Sauvegarde du client dans la base de données
        Customer customer = customerRepository.save(CustomerDto.toEntity(dto));
        return CustomerDto.fromEntity(customer);
    }

    @Override
    public CustomerDto findById(Integer id) {
        if (id == null) {
            log.error("Customer ID is null");
            return null;
        }

        // Recherche du client par ID
        return customerRepository.findById(id)
            .map(CustomerDto::fromEntity)
            .orElseThrow(() -> new EntityNotFoundException(
                "Aucun client avec l'ID = " + id + " n'a été trouvé dans la BDD", 
                ErrorCode.CUSTOMER_NOT_FOUND
            ));
    }

    @Override
    public List<CustomerDto> findAll() {
        // Récupération de tous les clients
        return customerRepository.findAll().stream()
            .map(CustomerDto::fromEntity)
            .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("Customer ID is null");
            return;
        }

        // Suppression du client par ID
        customerRepository.deleteById(id);
    }
}
