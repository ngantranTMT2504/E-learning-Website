package com.course.service.iface;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.course.dto.request.CourseRequestDTO;
import com.course.dto.response.CourseResponseDTO;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

@Service
public interface CourseService {
	
	public CourseResponseDTO create(CourseRequestDTO courseRequestDTO);

	public List<CourseResponseDTO> findAll();

	public CourseResponseDTO update(Integer id, CourseRequestDTO courseRequestDTO);

	public CourseResponseDTO findById(Integer id);

	public CourseResponseDTO deleteById(Integer id);
	
	public ResponseEntity<?> getPagedCoursesByManyParams(
	        String keyword,
	        String title,
	        String slug,
	        Integer minPrice,
	        Integer maxPrice,
	        List<Integer> courseTypeIds,
	        List<Integer> teacherIds,
	        Integer accountId,
	        Double discount,
	        Date createdDate,
	        Date updatedDate,
	        Double averageRating,
	        Boolean status,
	        Pageable pageable
	);
}
