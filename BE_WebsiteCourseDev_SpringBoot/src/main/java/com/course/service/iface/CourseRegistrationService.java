package com.course.service.iface;

import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.course.dto.request.CourseRegistrationRequestDTO;
import com.course.dto.response.CourseRegistrationResponseDTO;

@Service
public interface CourseRegistrationService {
	
	public CourseRegistrationResponseDTO create(CourseRegistrationRequestDTO courseRegistrationRequestDTO);

	public List<CourseRegistrationResponseDTO> findAll();

	public CourseRegistrationResponseDTO update(Integer id, CourseRegistrationRequestDTO courseRegistrationRequestDTO);

	public CourseRegistrationResponseDTO findById(Integer id);

	public CourseRegistrationResponseDTO deleteById(Integer id);
	
	public ResponseEntity<?> getPagedCourseRegistrationsByManyParams(
			int page,
	        int size,
			String transactionCode,
			Integer minPrice,
			Integer maxPrice,
			Double commission,
			Double discount,
			Date registrationDate,
			Boolean status,
			List<Integer> studentIds,
			List<Integer> courseIds
	);
}
