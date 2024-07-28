package com.boroousseni.gestionstock.utils;

public interface Constants {

	  String APP_ROOT = "gestionstock/v1";

	  String SUPPLIER_ORDER_ENDPOINT = APP_ROOT + "/commandesfournisseurs";
	  String CREATE_SUPPLIER_ORDER_ENDPOINT = SUPPLIER_ORDER_ENDPOINT + "/create";
	  String FIND_SUPPLIER_ORDER_BY_ID_ENDPOINT = SUPPLIER_ORDER_ENDPOINT + "/{idCommandeFournisseur}";
	  String FIND_SUPPLIER_ORDER_BY_CODE_ENDPOINT = SUPPLIER_ORDER_ENDPOINT + "/filter/{codeCommandeFournisseur}";
	  String FIND_ALL_SUPPLIER_ORDER_ENDPOINT = SUPPLIER_ORDER_ENDPOINT + "/all";
	  String DELETE_SUPPLIER_ORDER_ENDPOINT = SUPPLIER_ORDER_ENDPOINT + "/delete/{idCommandeFournisseur}";

	  String COMPANY_ENDPOINT = APP_ROOT + "/entreprises";

	  String SUPPLIER_ENDPOINT = APP_ROOT + "/fournisseurs";

	  String USER_ENDPOINT = APP_ROOT + "/utilisateurs";

	  String SALE_ENDPOINT = APP_ROOT + "/ventes";

	  String AUTHENTICATION_ENDPOINT = APP_ROOT + "/auth";

}
