package com.course.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.course.dto.request.CourseRegistrationRequestDTO;
import com.course.dto.response.CourseRegistrationResponseDTO;
import com.course.exception.ResourceNotFoundException;
import com.course.mapper.CourseRegistrationMapper;
import com.course.model.CourseRegistration;
import com.course.repository.CourseRegistrationRepository;
import com.course.service.iface.CourseRegistrationFilterService;
import com.course.service.iface.CourseRegistrationService;

@Service
public class CourseRegistrationServiceImpl implements CourseRegistrationService {

	@Autowired
	private CourseRegistrationRepository courseRegistrationRepository;

	@Override
	public CourseRegistrationResponseDTO create(CourseRegistrationRequestDTO courseRegistrationRequestDTO) {
	    CourseRegistration courseRegistration = CourseRegistrationMapper.toEntity(courseRegistrationRequestDTO);
	    CourseRegistration saved = courseRegistrationRepository.save(courseRegistration);
	    CourseRegistrationResponseDTO responseDTO = CourseRegistrationMapper.toResponse(saved);
	    return responseDTO;
	}

	@Override
	public List<CourseRegistrationResponseDTO> findAll() {
	    List<CourseRegistrationResponseDTO> responseList = courseRegistrationRepository.findAll().stream()
	        .map(CourseRegistrationMapper::toResponse)
	        .sorted(Comparator.comparing(CourseRegistrationResponseDTO::getRegistrationDate).reversed()
	            .thenComparing(CourseRegistrationResponseDTO::getTotalPrice).reversed())
	        .collect(Collectors.toList());
	    return responseList;
	}

	@Override
	public CourseRegistrationResponseDTO update(Integer id, CourseRegistrationRequestDTO courseRegistrationRequestDTO) {
	    findById(id);
	    CourseRegistration updatedCourseRegistration = CourseRegistrationMapper.toEntity(courseRegistrationRequestDTO);
	    updatedCourseRegistration.setId(id);
	    CourseRegistration update = courseRegistrationRepository.save(updatedCourseRegistration);
	    CourseRegistrationResponseDTO responseDTO = CourseRegistrationMapper.toResponse(update);
	    return responseDTO;
	}

	@Override
	public CourseRegistrationResponseDTO findById(Integer id) {
	    CourseRegistration courseRegistration = courseRegistrationRepository.findById(id)
	        .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy giao dịch"));
	    CourseRegistrationResponseDTO responseDTO = CourseRegistrationMapper.toResponse(courseRegistration);
	    return responseDTO;
	}

	@Override
	public CourseRegistrationResponseDTO deleteById(Integer id) {
	    CourseRegistrationResponseDTO responseDTO = findById(id);
	    courseRegistrationRepository.deleteById(id);
	    return responseDTO;
	}

	@Override
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
	) {

	    Pageable pageable = PageRequest.of(page, size,
	            Sort.by("registrationDate").descending());

	    Specification<CourseRegistration> spec = CourseRegistrationFilterService.filterSpec(
	    		transactionCode, minPrice, maxPrice, commission, discount, 
	    		registrationDate, status, studentIds, courseIds
	    );

	    Page<CourseRegistration> list = courseRegistrationRepository.findAll(spec, pageable);
	    Page<CourseRegistrationResponseDTO> result = list.map(CourseRegistrationMapper::toResponse);

	    Map<String, Object> response = new HashMap<>();
	    response.put("data", result.getContent());
	    response.put("currentPage", result.getNumber());
	    response.put("totalItems", result.getTotalElements());
	    response.put("totalPages", result.getTotalPages());

	    return ResponseEntity.ok(response);
	}

}
