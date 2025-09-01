package com.course.mapper;

import com.course.dto.request.CommissionRequestDTO;
import com.course.dto.response.CommissionResponseDTO;
import com.course.model.Commission;

public class CommissionMapper {

	public static Commission toEntity(CommissionRequestDTO dto) {
		Commission commission = new Commission();

		commission.setPercentage(dto.getPercentage());
	

		commission.setApplicableDate(dto.getApplicableDate());

	

		return commission;
	}

	public static CommissionResponseDTO toResponse(Commission commission) {
		CommissionResponseDTO dto = new CommissionResponseDTO();
		dto.setPercentage(commission.getPercentage());
		dto.setApplicableDate(commission.getApplicableDate());

	
	

		// getCourse().getTeacher().getId()

		
		return dto;
	}
}
