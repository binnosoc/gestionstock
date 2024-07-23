package com.boroousseni.gestionstock.dto;

import com.boroousseni.gestionstock.models.BaseInfo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BaseInfoDto {
	private String name;

	private String firstname;

	private String phone;

	private String email;

	private String picture;

	private AddressDto address;
	
	public static BaseInfoDto fromEntity(BaseInfo baseInfo) {
		if(baseInfo==null) {
			return null;
		}
		
		return BaseInfoDto.builder()
				.name(baseInfo.getName())
				.firstname(baseInfo.getFirstname())
				.phone(baseInfo.getPhone())
				.email(baseInfo.getEmail())
				.picture(baseInfo.getPicture())
				.address(AddressDto.fromEntity(baseInfo.getAddress()))
				.build();
	}
	
	public static BaseInfo toEntity(BaseInfoDto baseInfoDto) {
		if(baseInfoDto==null) {
			return null;
		}
		
		BaseInfo baseInfo = new BaseInfo();
		baseInfo.setName(baseInfoDto.getName());
		baseInfo.setFirstname(baseInfoDto.getFirstname());
		baseInfo.setPhone(baseInfoDto.getPhone());
		baseInfo.setEmail(baseInfoDto.getEmail());
		baseInfo.setPicture(baseInfoDto.getPicture());
		baseInfo.setAddress(AddressDto.toEntity(baseInfoDto.getAddress()));
		
		return baseInfo;
	}
}
