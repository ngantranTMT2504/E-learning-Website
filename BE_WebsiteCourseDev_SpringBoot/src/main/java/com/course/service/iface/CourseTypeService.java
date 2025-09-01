package com.course.service.iface;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.course.dto.request.CourseTypeRequestDTO;
import com.course.dto.response.CourseTypeResponseDTO;

@Service
public interface CourseTypeService {
	public CourseTypeResponseDTO create(CourseTypeRequestDTO courseTypeRequestDTO);

	public List<CourseTypeResponseDTO> findAll();

	public CourseTypeResponseDTO update(Integer id, CourseTypeRequestDTO courseTypeRequestDTO);

	public CourseTypeResponseDTO findById(Integer id);

	public CourseTypeResponseDTO deleteById(Integer id);

	
	public ResponseEntity<?> getPagedCourseTypeByManyParams(
			
			String name, 
			Boolean status, 
			Pageable pageable
	        
	);
}
