package com.course.service.iface;


import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.course.dto.request.TeacherBankRequestDTO;
import com.course.dto.response.TeacherBankResponseDTO;

@Service
public interface TeacherBankService {
	public TeacherBankResponseDTO create(TeacherBankRequestDTO teacherBankRequestDTO);

	public List<TeacherBankResponseDTO> findAll();

	public TeacherBankResponseDTO update(Integer id, TeacherBankRequestDTO teacherBankRequestDTO);

	public TeacherBankResponseDTO findById(Integer id);

	public TeacherBankResponseDTO deleteById(Integer id);
	
	public ResponseEntity<?> getPagedTeacherBankByManyParams(
			String bankCode,
			String bankName,
			Integer accountNumber,
			Integer accountHolder,
			Date createDate,
			Date uploadedDate,
			Boolean isDefault, 
			List<Integer> teacherId,
			Pageable pageable
	);
}
