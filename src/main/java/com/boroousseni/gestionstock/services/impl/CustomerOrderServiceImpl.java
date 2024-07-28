package com.boroousseni.gestionstock.services.impl;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boroousseni.gestionstock.dto.CustomerDto;
import com.boroousseni.gestionstock.dto.CustomerOrderDto;
import com.boroousseni.gestionstock.dto.CustomerOrderLigneDto;
import com.boroousseni.gestionstock.dto.ItemDto;
import com.boroousseni.gestionstock.dto.StockMovementDto;
import com.boroousseni.gestionstock.exceptions.EntityNotFoundException;
import com.boroousseni.gestionstock.exceptions.ErrorCode;
import com.boroousseni.gestionstock.exceptions.InvalidEntityException;
import com.boroousseni.gestionstock.exceptions.InvalidOperationException;
import com.boroousseni.gestionstock.models.Customer;
import com.boroousseni.gestionstock.models.CustomerOrder;
import com.boroousseni.gestionstock.models.CustomerOrderLigne;
import com.boroousseni.gestionstock.models.Item;
import com.boroousseni.gestionstock.models.OrderStatus;
import com.boroousseni.gestionstock.models.StockSource;
import com.boroousseni.gestionstock.models.TypeOfStock;
import com.boroousseni.gestionstock.repository.CustomerOrderLigneRepository;
import com.boroousseni.gestionstock.repository.CustomerOrderRepository;
import com.boroousseni.gestionstock.repository.CustomerRepository;
import com.boroousseni.gestionstock.repository.ItemRepository;
import com.boroousseni.gestionstock.services.CustomerOrderServcie;
import com.boroousseni.gestionstock.services.StockMovementService;
import com.boroousseni.gestionstock.validators.CustomerOrderValidator;
import com.boroousseni.gestionstock.validators.ItemValidator;

import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerOrderServiceImpl implements CustomerOrderServcie {

	private CustomerOrderRepository customerOrderRepository;
	private CustomerOrderLigneRepository customerOrderLigneRepository;
	private CustomerRepository customerRepository;
	private ItemRepository itemRepository;
	private StockMovementService stockMovementService;

	@Autowired
	public CustomerOrderServiceImpl(CustomerOrderRepository customerOrderRepository,
			CustomerRepository customerRepository, ItemRepository itemRepository,
			CustomerOrderLigneRepository customerOrderLigneRepository, StockMovementService stockMovementService) {
		this.customerOrderRepository = customerOrderRepository;
		this.customerOrderLigneRepository = customerOrderLigneRepository;
		this.customerRepository = customerRepository;
		this.itemRepository = itemRepository;
		this.stockMovementService = stockMovementService;
	}

	@Override
	public CustomerOrderDto save(CustomerOrderDto dto) {
		// TODO Auto-generated method stub
		List<String> errors = CustomerOrderValidator.validate(dto);

		if (!errors.isEmpty()) {
			log.error("Order customer n'est pas valide");
			throw new InvalidEntityException("La order customer n'est pas valide", ErrorCode.CUSTOMER_ORDER_NOT_VALID,
					errors);
		}

		if (dto.getId() != null && dto.isDeliveredOrder()) {
			throw new InvalidOperationException("Impossible de modifier la order lorsqu'elle est livree",
					ErrorCode.CUSTOMER_ORDER_NOT_EDITABLE);
		}

		Optional<Customer> customer = customerRepository.findById(dto.getCustomer().getId());
		if (customer.isEmpty()) {
			log.warn("Customer with ID {} was not found in the DB", dto.getCustomer().getId());
			throw new EntityNotFoundException(
					"Aucun customer avec l'ID" + dto.getCustomer().getId() + " n'a ete trouve dans la BDD",
					ErrorCode.CUSTOMER_NOT_FOUND);
		}

		List<String> errorsItem = new ArrayList<>();

		if (dto.getCustomerOrdersLigne() != null) {
			dto.getCustomerOrdersLigne().forEach(ligCmdClt -> {
				if (ligCmdClt.getItem() != null) {
					Optional<Item> item = itemRepository.findById(ligCmdClt.getItem().getId());
					if (item.isEmpty()) {
						errorsItem.add("L'item avec l'ID " + ligCmdClt.getItem().getId() + " n'existe pas");
					}
				} else {
					errorsItem.add("Impossible d'enregister une order avec un aticle NULL");
				}
			});
		}

		if (!errorsItem.isEmpty()) {
			log.warn("");
			throw new InvalidEntityException("Item n'existe pas dans la BDD", ErrorCode.ITEM_NOT_FOUND, errorsItem);
		}
		dto.setOrderDate(Instant.now());
		CustomerOrder savedCmdClt = customerOrderRepository.save(CustomerOrderDto.toEntity(dto));

		if (dto.getCustomerOrdersLigne() != null) {
			dto.getCustomerOrdersLigne().forEach(ligCmdClt -> {
				CustomerOrderLigne customerOrderLigne = CustomerOrderLigneDto.toEntity(ligCmdClt);
				customerOrderLigne.setCustomerOrder(savedCmdClt);
				CustomerOrderLigne savedLigneCmd = customerOrderLigneRepository.save(customerOrderLigne);

				doOutput(savedLigneCmd);
			});
		}

		return CustomerOrderDto.fromEntity(savedCmdClt);
	}

	@Override
	public CustomerOrderDto updateOrderStatus(Integer orderID, OrderStatus orderStatus) {
		// TODO Auto-generated method stub
		checkOrderId(orderID);
		if (StringUtils.isEmpty(String.valueOf(orderStatus))) {
			log.error("L'etat de la order customer is NULL");
			throw new InvalidOperationException("Impossible de modifier l'etat de la order avec un etat null",
					ErrorCode.CUSTOMER_ORDER_NOT_EDITABLE);
		}
		CustomerOrderDto orderCustomer = checkOrderStatus(orderID);
		orderCustomer.setStatus(orderStatus);

		CustomerOrder savedCmdClt = customerOrderRepository.save(CustomerOrderDto.toEntity(orderCustomer));
		if (orderCustomer.isDeliveredOrder()) {
			updateMvtStk(orderID);
		}

		return CustomerOrderDto.fromEntity(savedCmdClt);
	}

	@Override
	public CustomerOrderDto updateOrderQuantity(Integer orderID, Integer orderLigneID, Integer quantity) {
		// TODO Auto-generated method stub
		checkOrderId(orderID);
		checkOrderLigneId(orderLigneID);

		if (quantity == null || quantity.compareTo(Integer.SIZE) == 0) {
			log.error("L'ID de la ligne order is NULL");
			throw new InvalidOperationException(
					"Impossible de modifier l'etat de la order avec une quantity null ou ZERO",
					ErrorCode.CUSTOMER_ORDER_NOT_EDITABLE);
		}

		CustomerOrderDto orderCustomer = checkOrderStatus(orderID);
		Optional<CustomerOrderLigne> customerOrderLigneOptional = findCustomerOrderLigne(orderLigneID);

		CustomerOrderLigne customerOrderLigne = customerOrderLigneOptional.get();
//		    customerOrderLigne.setQuantity(quantity);
		customerOrderLigneRepository.save(customerOrderLigne);

		return orderCustomer;

	}

	@Override
	public CustomerOrderDto updateCustomer(Integer orderID, Integer idCustomer) {
		// TODO Auto-generated method stub
		checkOrderId(orderID);
		if (idCustomer == null) {
			log.error("L'ID du customer is NULL");
			throw new InvalidOperationException("Impossible de modifier l'etat de la order avec un ID customer null",
					ErrorCode.CUSTOMER_ORDER_NOT_EDITABLE);
		}
		CustomerOrderDto orderCustomer = checkOrderStatus(orderID);
		Optional<Customer> customerOptional = customerRepository.findById(idCustomer);
		if (customerOptional.isEmpty()) {
			throw new EntityNotFoundException("Aucun customer n'a ete trouve avec l'ID " + idCustomer,
					ErrorCode.CUSTOMER_NOT_FOUND);
		}
		orderCustomer.setCustomer(CustomerDto.fromEntity(customerOptional.get()));

		return CustomerOrderDto.fromEntity(customerOrderRepository.save(CustomerOrderDto.toEntity(orderCustomer)));
	}

	@Override
	public CustomerOrderDto updateItem(Integer orderID, Integer orderLigneID, Integer itemID) {
		// TODO Auto-generated method stub
		checkOrderId(orderID);
		checkOrderLigneId(orderLigneID);
		checkIdItem(itemID, "nouvel");

		CustomerOrderDto orderCustomer = checkOrderStatus(orderID);

		Optional<CustomerOrderLigne> customerOrderLigne = findCustomerOrderLigne(orderLigneID);

		Optional<Item> itemOptional = itemRepository.findById(itemID);
		if (itemOptional.isEmpty()) {
			throw new EntityNotFoundException("Aucune item n'a ete trouve avec l'ID " + itemID,
					ErrorCode.ITEM_NOT_FOUND);
		}

		List<String> errors = ItemValidator.validate(ItemDto.fromEntity(itemOptional.get()));
		if (!errors.isEmpty()) {
			throw new InvalidEntityException("Item invalid", ErrorCode.ITEM_NOT_VALID, errors);
		}

		CustomerOrderLigne customerOrderLigneToSaved = customerOrderLigne.get();
		customerOrderLigneToSaved.setItem(itemOptional.get());
		customerOrderLigneRepository.save(customerOrderLigneToSaved);

		return orderCustomer;
	}

	@Override
	public CustomerOrderDto deleteItem(Integer orderID, Integer orderLigneID) {
		// TODO Auto-generated method stub
		checkOrderId(orderID);
		checkOrderLigneId(orderLigneID);

		CustomerOrderDto orderCustomer = checkOrderStatus(orderID);
		// Just to check the CustomerOrderLigne and inform the customer in case it is
		// absent
		findCustomerOrderLigne(orderLigneID);
		customerOrderLigneRepository.deleteById(orderLigneID);

		return orderCustomer;
	}



	@Override
	public CustomerOrderDto findById(Integer id) {
		// TODO Auto-generated method stub
		if (id == null) {
			log.error("Order customer ID is NULL");
			return null;
		}
		return customerOrderRepository.findById(id).map(CustomerOrderDto::fromEntity)
				.orElseThrow(() -> new EntityNotFoundException("Aucune order customer n'a ete trouve avec l'ID " + id,
						ErrorCode.CUSTOMER_ORDER_NOT_FOUND));
	}


	@Override
	public List<CustomerOrderDto> findAll() {
		// TODO Auto-generated method stub
		return customerOrderRepository.findAll().stream().map(CustomerOrderDto::fromEntity)
				.collect(Collectors.toList());
	}

	@Override
	public List<CustomerOrderLigneDto> findAllCustomerOrderLigneByCustomerOrderId(Integer orderID) {
		// TODO Auto-generated method stub
		return customerOrderLigneRepository.findAllByCustomerOrderId(orderID).stream()
				.map(CustomerOrderLigneDto::fromEntity).collect(Collectors.toList());
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		if (id == null) {
			log.error("Order customer ID is NULL");
			return;
		}
		List<CustomerOrderLigne> customerOrderLigne = customerOrderLigneRepository.findAllByCustomerOrderId(id);
		if (!customerOrderLigne.isEmpty()) {
			throw new InvalidOperationException("Impossible de supprimer une order customer deja utilisee",
					ErrorCode.CUSTOMER_ORDER_ALREADY_IN_USE);
		}
		customerOrderRepository.deleteById(id);
	}

	private CustomerOrderDto checkOrderStatus(Integer orderID) {
		CustomerOrderDto commandeClient = findById(orderID);
		if (commandeClient.isDeliveredOrder()) {
			throw new InvalidOperationException("Impossible de modifier la commande lorsqu'elle est livree",
					ErrorCode.CUSTOMER_ORDER_NOT_EDITABLE);
		}
		return commandeClient;
	}

	private Optional<CustomerOrderLigne> findCustomerOrderLigne(Integer idLigneCommande) {
		Optional<CustomerOrderLigne> customerOrderLigneOptional = customerOrderLigneRepository
				.findById(idLigneCommande);
		if (customerOrderLigneOptional.isEmpty()) {
			throw new EntityNotFoundException(
					"Aucune ligne commande client n'a ete trouve avec l'ID " + idLigneCommande,
					ErrorCode.CUSTOMER_ORDER_NOT_FOUND);
		}
		return customerOrderLigneOptional;
	}

	private void checkOrderId(Integer orderID) {
		if (orderID == null) {
			log.error("Commande client ID is NULL");
			throw new InvalidOperationException("Impossible de modifier l'etat de la commande avec un ID null",
					ErrorCode.CUSTOMER_ORDER_NOT_EDITABLE);
		}
	}

	private void checkOrderLigneId(Integer idLigneCommande) {
		if (idLigneCommande == null) {
			log.error("L'ID de la ligne commande is NULL");
			throw new InvalidOperationException(
					"Impossible de modifier l'etat de la commande avec une ligne de commande null",
					ErrorCode.CUSTOMER_ORDER_NOT_EDITABLE);
		}
	}

	private void checkIdItem(Integer idItem, String msg) {
		if (idItem == null) {
			log.error("L'ID de " + msg + " is NULL");
			throw new InvalidOperationException(
					"Impossible de modifier l'etat de la commande avec un " + msg + " ID item null",
					ErrorCode.CUSTOMER_ORDER_NOT_EDITABLE);
		}
	}

	private void updateMvtStk(Integer orderID) {
		List<CustomerOrderLigne> customerOrderLignes = customerOrderLigneRepository.findAllByCustomerOrderId(orderID);
		customerOrderLignes.forEach(lig -> {
			doOutput(lig);
		});
	}

	private void doOutput(CustomerOrderLigne lig) {
		StockMovementDto stockMovementDto = StockMovementDto.builder().item(ItemDto.fromEntity(lig.getItem()))
				.stockMovementDate(Instant.now()).typeOfStock(TypeOfStock.SORTIE)
				.stockSource(StockSource.COMMANDE_CLIENT).build();
		stockMovementService.sortieStock(stockMovementDto);
	}

	@Override
	public CustomerOrderDto findByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

}
