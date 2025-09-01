package com.course.service.iface;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.course.dto.request.CommissionRequestDTO;
import com.course.dto.response.CommissionResponseDTO;

@Service
public interface CommissionService {
	public CommissionResponseDTO create(CommissionRequestDTO commissionRequestDTO);

	public List<CommissionResponseDTO> findAll();

	public CommissionResponseDTO update(Integer id, CommissionRequestDTO commissionRequestDTO);

	public CommissionResponseDTO findById(Integer id);

	public CommissionResponseDTO deleteById(Integer id);

	public ResponseEntity<?> getPagedCommissionByManyParams(

			Double percentage,

			Date applicableDate,
			Pageable pageable

	);
}
