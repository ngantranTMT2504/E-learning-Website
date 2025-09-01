package com.course.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.dto.request.UpdateProfileRequestDTO;
import com.course.dto.response.AccountProfileResponseDTO;
import com.course.exception.ResourceNotFoundException;
import com.course.mapper.UpdateProfileMapper;
import com.course.model.Account;
import com.course.repository.AccountRepository;
import com.course.service.iface.UpdateProfileService;

@Service
public class UpdateProfileServiceImpl implements UpdateProfileService {

	@Autowired
	private AccountRepository accountRepository;
	
	@Override
	public AccountProfileResponseDTO update(Integer id, UpdateProfileRequestDTO dto) {
	    Account account = accountRepository.findById(id)
	        .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy tài khoản"));
	    
	    account.setEmail(dto.getEmail());
	    account.setFullname(dto.getFullname());
	    account.setGender(dto.getGender());
	    account.setBirthday(dto.getBirthday());
	    account.setAvatar(dto.getAvatar());

	    Account updated = accountRepository.save(account);
	    return UpdateProfileMapper.toResponse(updated);
	}


	
}
