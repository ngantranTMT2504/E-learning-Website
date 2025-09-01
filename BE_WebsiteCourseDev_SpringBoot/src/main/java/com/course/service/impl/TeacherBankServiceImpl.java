package com.course.service.impl;

import java.util.Comparator;
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

import com.course.dto.request.TeacherBankRequestDTO;
import com.course.dto.response.TeacherBankResponseDTO;
import com.course.exception.ResourceNotFoundException;
import com.course.mapper.TeacherBankMapper;
import com.course.model.TeacherBank;
import com.course.repository.TeacherBankRepository;
import com.course.service.iface.TeacherBankFilterService;
import com.course.service.iface.TeacherBankService;

@Service
public class TeacherBankServiceImpl implements TeacherBankService {

	@Autowired
	private TeacherBankRepository teacherBankRepository;

	@Override
	public TeacherBankResponseDTO create(TeacherBankRequestDTO teacherBankRequestDTO) {
		TeacherBank teacherBank = TeacherBankMapper.toEntity(teacherBankRequestDTO);
		TeacherBank saved = teacherBankRepository.save(teacherBank);
		TeacherBankResponseDTO teacherBankResponseDTO = TeacherBankMapper.toResponse(saved);
		return teacherBankResponseDTO;
	}

	@Override
	public List<TeacherBankResponseDTO> findAll() {
		List<TeacherBankResponseDTO> responseList = teacherBankRepository.findAll().stream()
				.map(TeacherBankMapper::toResponse)
				.sorted(Comparator.comparing(TeacherBankResponseDTO::getAccountHolder).reversed()

						.thenComparing(TeacherBankResponseDTO::getBankName).reversed()
						.thenComparing(TeacherBankResponseDTO::getCreateDate).reversed()
						.thenComparing(TeacherBankResponseDTO::getUploadedDate).reversed())

				.collect(Collectors.toList());
		return responseList;
	}

	@Override
	public TeacherBankResponseDTO update(Integer id, TeacherBankRequestDTO teacherBankRequestDTO) {
		findById(id);
		TeacherBank updatedTeacherBank = TeacherBankMapper.toEntity(teacherBankRequestDTO);
		updatedTeacherBank.setId(id);
		TeacherBank update = teacherBankRepository.save(updatedTeacherBank);
		TeacherBankResponseDTO teacherBankResponseDTO = TeacherBankMapper.toResponse(update);
		return teacherBankResponseDTO;
	}

	@Override
	public TeacherBankResponseDTO findById(Integer id) {
		TeacherBank teacherBank = teacherBankRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Không tìm thẻ ngân hàng giáo viên"));
		TeacherBankResponseDTO teacherBankResponseDTO = TeacherBankMapper.toResponse(teacherBank);
		return teacherBankResponseDTO;
	}

	@Override
	public TeacherBankResponseDTO deleteById(Integer id) {
		TeacherBankResponseDTO teacherBankResponseDTO = findById(id);
		teacherBankRepository.deleteById(id);
		return teacherBankResponseDTO;
	}

	@Override
	public ResponseEntity<?> getPagedTeacherBankByManyParams(String bankCode, String bankName, Integer accountNumber,
			Integer accountHolder, Date createDate, Date uploadedDate, Boolean isDefault, List<Integer> teacherId,
			Pageable pageable) {

		Specification<TeacherBank> spec = TeacherBankFilterService.filterSpec(bankName, createDate, uploadedDate,
				isDefault, teacherId);

		Page<TeacherBank> pageResult = teacherBankRepository.findAll(spec, pageable);
		List<TeacherBankResponseDTO> result = pageResult.stream().map(TeacherBankMapper::toResponse).toList();

		Map<String, Object> response = new HashMap<>();
		response.put("data", result);
		response.put("currentPage", pageResult.getNumber());
		response.put("totalItems", pageResult.getTotalElements());
		response.put("totalPages", pageResult.getTotalPages());

		System.out.println("Sort BE nhận: " + pageable.getSort());

		return ResponseEntity.ok(response);

	}
//	

}
