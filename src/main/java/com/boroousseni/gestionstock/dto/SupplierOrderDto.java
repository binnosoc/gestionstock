package com.boroousseni.gestionstock.dto;

import java.time.Instant;
import java.util.List;

import com.boroousseni.gestionstock.models.OrderStatus;
import com.boroousseni.gestionstock.models.SupplierOrder;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SupplierOrderDto {
	 
		private Integer supplierOrderID;
	 
		private Instant orderDate;
	 
		private Float totalAmount;
		 
		private Integer quantity;
		
		private OrderStatus status;
	 
		private SupplierDto supplier;
	 
		@JsonIgnore
		private List<SupplierOrderLigneDto> supplierOrdersLigne;

		public static SupplierOrderDto fromEntity(SupplierOrder supplierOrder) {
			if(supplierOrder==null) {
				return null;
			}
			return SupplierOrderDto.builder()
					.supplierOrderID(supplierOrder.getSupplierOrderID())
					.orderDate(supplierOrder.getOrderDate())
					.totalAmount(supplierOrder.getTotalAmount())
					.quantity(supplierOrder.getQuantity())
					.status(supplierOrder.getStatus())
					.supplier(SupplierDto.fromEntity(supplierOrder.getSupplier()))
					.build()
					;
		}

		public static SupplierOrder toEntity(SupplierOrderDto supplierOrderDto) {
			if(supplierOrderDto ==null) {
				return null;
			}
			
			SupplierOrder supplierOrder = new SupplierOrder();
			
			supplierOrder.setSupplierOrderID(supplierOrderDto.getSupplierOrderID());
			supplierOrder.setOrderDate(supplierOrderDto.getOrderDate());
			supplierOrder.setTotalAmount(supplierOrderDto.getTotalAmount());
			supplierOrder.setQuantity(supplierOrderDto.getQuantity());
			supplierOrder.setStatus(supplierOrderDto.getStatus());
			supplierOrder.setSupplier(SupplierDto.toEntity(supplierOrderDto.getSupplier()));
			
			return supplierOrder;
			
		}	
		public boolean isDeliveredOrder() {
			return OrderStatus.LIVREE.equals(this.status);
		}
}
