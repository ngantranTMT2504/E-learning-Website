package com.course.mapper;

import com.course.dto.request.CourseTypeRequestDTO;
import com.course.dto.response.CourseTypeResponseDTO;
import com.course.model.CourseType;

public class CourseTypeMapper {

	public static CourseType toEntity(CourseTypeRequestDTO dto) {
		CourseType courseType = new CourseType();

		courseType.setName(dto.getName());
	

		courseType.setStatus(dto.isStatus());

	

		return courseType;
	}

	public static CourseTypeResponseDTO toResponse(CourseType courseType) {
		CourseTypeResponseDTO dto = new CourseTypeResponseDTO();
		dto.setId(courseType.getId());
		dto.setName(courseType.getName());

	
		dto.setStatus(courseType.isStatus());

		// getCourse().getTeacher().getId()

		
		return dto;
	}
}
