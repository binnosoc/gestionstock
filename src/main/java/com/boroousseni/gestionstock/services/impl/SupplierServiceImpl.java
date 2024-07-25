package com.boroousseni.gestionstock.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boroousseni.gestionstock.dto.SupplierDto;
import com.boroousseni.gestionstock.exceptions.EntityNotFoundException;
import com.boroousseni.gestionstock.exceptions.ErrorCode;
import com.boroousseni.gestionstock.exceptions.InvalidEntityException;
import com.boroousseni.gestionstock.exceptions.InvalidOperationException;
import com.boroousseni.gestionstock.models.CustomerOrder;
import com.boroousseni.gestionstock.repository.SupplierOrderRepository;
import com.boroousseni.gestionstock.repository.SupplierRepository;
import com.boroousseni.gestionstock.services.SupplierServcice;
import com.boroousseni.gestionstock.validators.SupplierValidator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SupplierServiceImpl implements SupplierServcice {

	private SupplierRepository supplierRepository;
	private SupplierOrderRepository supplierOrderRepository;

	@Autowired
	public SupplierServiceImpl(SupplierRepository supplierRepository, SupplierOrderRepository supplierOrderRepository) {
		this.supplierRepository = supplierRepository;
		this.supplierOrderRepository = supplierOrderRepository;
	}

	@Override
	public SupplierDto save(SupplierDto dto) {
		// TODO Auto-generated method stub
		List<String> errors = SupplierValidator.validate(dto);
		if (!errors.isEmpty()) {
			log.error("Supplier is not valid {}", dto);
			throw new InvalidEntityException("Le supplier n'est pas valide", ErrorCode.SUPPLIER_NOT_VALID, errors);
		}

		return SupplierDto.fromEntity(supplierRepository.save(SupplierDto.toEntity(dto)));
	}

	@Override
	public SupplierDto findById(Integer id) {
		// TODO Auto-generated method stub
		if (id == null) {
			log.error("Supplier ID is null");
			return null;
		}
		return supplierRepository.findById(id).map(SupplierDto::fromEntity)
				.orElseThrow(() -> new EntityNotFoundException(
						"Aucun supplier avec l'ID = " + id + " n' ete trouve dans la BDD",
						ErrorCode.SUPPLIER_NOT_FOUND));
	}

	@Override
	public List<SupplierDto> findAll() {
		// TODO Auto-generated method stub
		return supplierRepository.findAll().stream().map(SupplierDto::fromEntity).collect(Collectors.toList());
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		if (id == null) {
			log.error("Supplier ID is null");
			return;
		}
		List<CustomerOrder> supplierOrder = supplierOrderRepository.findAllBySupplierId(id);
		if (!supplierOrder.isEmpty()) {
			throw new InvalidOperationException("Impossible de supprimer un fournisseur qui a deja des commandes",
					ErrorCode.SUPPLIER_ALREADY_IN_USE);
		}
		supplierRepository.deleteById(id);
	}

}
