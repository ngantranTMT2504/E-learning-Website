package com.course.service.impl;

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

import com.course.dto.request.CourseTypeRequestDTO;
import com.course.dto.response.CourseTypeResponseDTO;
import com.course.exception.ResourceNotFoundException;
import com.course.mapper.CourseTypeMapper;
import com.course.model.CourseType;
import com.course.repository.CourseTypeRepository;
import com.course.service.iface.CourseTypeFilterService;
import com.course.service.iface.CourseTypeService;

@Service
public class CourseTypeServiceImpl implements CourseTypeService {

	@Autowired
	private CourseTypeRepository courseTypeRepository;

	@Override
	public CourseTypeResponseDTO create(CourseTypeRequestDTO courseTypeRequestDTO) {
		CourseType courseType = CourseTypeMapper.toEntity(courseTypeRequestDTO);
		CourseType saved = courseTypeRepository.save(courseType);
		CourseTypeResponseDTO courseTypeResponseDTO = CourseTypeMapper.toResponse(saved);
		return courseTypeResponseDTO;
	}

	@Override
	public List<CourseTypeResponseDTO> findAll() {
		List<CourseTypeResponseDTO> responseList = courseTypeRepository.findAll().stream().map(CourseTypeMapper::toResponse)
				
				.collect(Collectors.toList());
		return responseList;
	}

	@Override
	public CourseTypeResponseDTO update(Integer id, CourseTypeRequestDTO courseTypeRequestDTO) {
		findById(id);
		CourseType updatedCourseType = CourseTypeMapper.toEntity(courseTypeRequestDTO);
		updatedCourseType.setId(id);
		CourseType update = courseTypeRepository.save(updatedCourseType);
		CourseTypeResponseDTO courseTypeResponseDTO = CourseTypeMapper.toResponse(update);
		return courseTypeResponseDTO;
	}

	@Override
	public CourseTypeResponseDTO findById(Integer id) {
		CourseType courseType = courseTypeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy bài học"));
		CourseTypeResponseDTO courseTypeResponseDTO = CourseTypeMapper.toResponse(courseType);
		return courseTypeResponseDTO;
	}

	@Override
	public CourseTypeResponseDTO deleteById(Integer id) {
		CourseTypeResponseDTO courseTypeResponseDTO = findById(id);
		courseTypeRepository.deleteById(id);
		return courseTypeResponseDTO;
	}
	
	@Override
	public ResponseEntity<?> getPagedCourseTypeByManyParams(String name,Boolean status, Pageable pageable

	) {
		Specification<CourseType> spec = CourseTypeFilterService.filterSpec(name,status);

		Page<CourseType> pageResult = courseTypeRepository.findAll(spec, pageable);
		List<CourseTypeResponseDTO> result = pageResult.stream().map(CourseTypeMapper::toResponse).toList();

		Map<String, Object> response = new HashMap<>();
		response.put("data", result);
		response.put("currentPage", pageResult.getNumber());
		response.put("totalItems", pageResult.getTotalElements());
		response.put("totalPages", pageResult.getTotalPages());

		return ResponseEntity.ok(response);
	}
	


}
