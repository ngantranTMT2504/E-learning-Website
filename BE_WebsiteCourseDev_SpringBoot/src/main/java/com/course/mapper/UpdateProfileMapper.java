package com.course.mapper;

import com.course.dto.response.AccountProfileResponseDTO;
import com.course.model.Account;

public class UpdateProfileMapper {
		
	public static AccountProfileResponseDTO toResponse(Account account) {
		AccountProfileResponseDTO dto = new AccountProfileResponseDTO();
		
		dto.setId(account.getId());
		dto.setEmail(account.getEmail());
		dto.setFullname(account.getFullname());
		dto.setGender(account.getGender());
		dto.setBirthday(account.getBirthday());
		dto.setAvatar(account.getAvatar());
		dto.setStatus(account.isStatus());
		dto.setRegisteredDate(account.getRegistrationDate());
		
		return dto;
	}
}
