package com.course.service.iface;

import org.springframework.stereotype.Service;

import com.course.dto.request.UpdateProfileRequestDTO;
import com.course.dto.response.AccountProfileResponseDTO;

@Service
public interface UpdateProfileService {
	public AccountProfileResponseDTO update(Integer id, UpdateProfileRequestDTO account);
}
