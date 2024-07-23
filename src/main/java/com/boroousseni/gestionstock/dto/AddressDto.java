package com.boroousseni.gestionstock.dto;

import com.boroousseni.gestionstock.models.Address;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressDto {

	private String address1;

	private String address2;

	private String country;

	private String town;

	private String postalCode;

	public static AddressDto fromEntity(Address address) {
		if(address == null) {
			return null;
		}
		
		
		
		return AddressDto.builder()
				.address1(address.getAddress1())
				.address2(address.getAddress2())
				.country(address.getCountry())
				.town(address.getTown())
				.postalCode(address.getPostalCode())
				.build();
	}

	public static Address toEntity(AddressDto addressDto) {
		if (addressDto == null) {
			return null;
		}
		
		Address address = new Address();
		
		address.setAddress1(addressDto.getAddress1());
		address.setAddress2(addressDto.getAddress2());
		address.setCountry(addressDto.getCountry());
		address.setTown(addressDto.getTown());
		address.setPostalCode(addressDto.getPostalCode());
		return address;
		
	}
}
