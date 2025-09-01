package com.course.service.iface;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.course.dto.request.LessonRequestDTO;
import com.course.dto.response.LessonResponseDTO;

@Service
public interface LessonService {
	public LessonResponseDTO create(LessonRequestDTO lessonRequestDTO);

	public List<LessonResponseDTO> findAll();

	public LessonResponseDTO update(Integer id, LessonRequestDTO lessonRequestDTO);

	public LessonResponseDTO findById(Integer id);

	public LessonResponseDTO deleteById(Integer id);
	
	public ResponseEntity<?> getPagedLessonsByManyParams(
			Date postedDate,
			Integer lessonOrder,
			String title,
			String courseName, 
			Boolean status, 
			List<Integer> courseIds,
			List<Integer> teacherIds,
			Integer accountId,
			Pageable pageable
	        
	);
}
