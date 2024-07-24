package com.boroousseni.gestionstock.services;

import java.util.List;

import com.boroousseni.gestionstock.dto.SupplierOrderDto;
import com.boroousseni.gestionstock.dto.SupplierOrderLigneDto;
import com.boroousseni.gestionstock.models.OrderStatus;

public interface SupplierOrderServcice {

	  SupplierOrderDto save(SupplierOrderDto dto);

	  SupplierOrderDto updateEtatCommande(Integer orderID, OrderStatus orderStatus);

	  SupplierOrderDto updateQuantiteCommande(Integer orderID, Integer idLigneCommande, Integer quantity);

	  SupplierOrderDto updateFournisseur(Integer orderID, Integer supplierID);

	  SupplierOrderDto updateArticle(Integer orderID, Integer orderLigneID, Integer itemID);

	  // Delete article ==> delete LigneCommandeFournisseur
	  SupplierOrderDto deleteArticle(Integer orderID, Integer orderLigneID);

	  SupplierOrderDto findById(Integer id);

	  SupplierOrderDto findByCode(String code);

	  List<SupplierOrderDto> findAll();

	  List<SupplierOrderLigneDto> findAllLignesCommandesFournisseurByCommandeFournisseurId(Integer orderID);

	  void delete(Integer id);

}
