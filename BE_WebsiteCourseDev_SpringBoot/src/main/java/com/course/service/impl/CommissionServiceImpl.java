package com.course.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.course.dto.request.CommissionRequestDTO;
import com.course.dto.response.CommissionResponseDTO;
import com.course.exception.ResourceNotFoundException;
import com.course.mapper.CommissionMapper;
import com.course.model.Commission;
import com.course.repository.CommissionRepository;
import com.course.service.iface.CommissionFilterService;
import com.course.service.iface.CommissionService;

@Service
public class CommissionServiceImpl implements CommissionService {

	@Autowired
	private CommissionRepository commissionRepository;

	@Override
	public CommissionResponseDTO create(CommissionRequestDTO commissionRequestDTO) {
		Commission commission = CommissionMapper.toEntity(commissionRequestDTO);
		Commission saved = commissionRepository.save(commission);
		CommissionResponseDTO commissionResponseDTO = CommissionMapper.toResponse(saved);
		return commissionResponseDTO;
	}

	@Override
	public List<CommissionResponseDTO> findAll() {
		List<CommissionResponseDTO> responseList = commissionRepository.findAll().stream().map(CommissionMapper::toResponse)
				
				.collect(Collectors.toList());
		return responseList;
	}

	@Override
	public CommissionResponseDTO update(Integer id, CommissionRequestDTO commissionRequestDTO) {
		findById(id);
		Commission updatedCommission = CommissionMapper.toEntity(commissionRequestDTO);
		updatedCommission.setId(id);
		Commission update = commissionRepository.save(updatedCommission);
		CommissionResponseDTO commissionResponseDTO = CommissionMapper.toResponse(update);
		return commissionResponseDTO;
	}

	@Override
	public CommissionResponseDTO findById(Integer id) {
		Commission commission = commissionRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy chiết khấu"));
		CommissionResponseDTO commissionResponseDTO = CommissionMapper.toResponse(commission);
		return commissionResponseDTO;
	}

	@Override
	public CommissionResponseDTO deleteById(Integer id) {
		CommissionResponseDTO commissionResponseDTO = findById(id);
		commissionRepository.deleteById(id);
		return commissionResponseDTO;
	}
	
	@Override
	public ResponseEntity<?> getPagedCommissionByManyParams(Double percentage, Date applicableDate, Pageable pageable) {
		Specification<Commission> spec = CommissionFilterService.filterSpec(percentage,applicableDate);

		Page<Commission> pageResult = commissionRepository.findAll(spec, pageable);
		List<CommissionResponseDTO> result = pageResult.stream().map(CommissionMapper::toResponse).toList();

		Map<String, Object> response = new HashMap<>();
		response.put("data", result);
		response.put("currentPage", pageResult.getNumber());
		response.put("totalItems", pageResult.getTotalElements());
		response.put("totalPages", pageResult.getTotalPages());

		return ResponseEntity.ok(response);
	}


}
