package com.course.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.course.dto.request.TeacherRequestDTO;
import com.course.dto.response.TeacherResponseDTO;
import com.course.model.Teacher;

public class TeacherMapper {
	public static Teacher toEntity(TeacherRequestDTO dto) {
		Teacher teacher = new Teacher();
		teacher.setDegree(dto.getDegree());
		teacher.setPhone(dto.getPhone());
		teacher.setEducationLevel(dto.getEducationLevel());
		teacher.setStatus(dto.isStatus());
		return teacher;
	}

	public static TeacherResponseDTO toResponse(Teacher teacher) {
		TeacherResponseDTO dto = new TeacherResponseDTO();
		dto.setId(teacher.getId());
		dto.setDegree(teacher.getDegree());
		dto.setPhone(teacher.getPhone());
		dto.setEducationLevel(teacher.getEducationLevel());
		dto.setStatus(teacher.isStatus());
		if (teacher.getAccount() != null) {
			dto.setAccountId(teacher.getAccount().getId());
		}
		return dto;
	}

	public static List<TeacherResponseDTO> toResponseDTOList(List<Teacher> teachers) {
		return teachers.stream().map(TeacherMapper::toResponse).collect(Collectors.toList());
	}
}
