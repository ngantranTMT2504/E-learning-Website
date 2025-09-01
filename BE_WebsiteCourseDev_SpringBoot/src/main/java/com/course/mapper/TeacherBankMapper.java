package com.course.mapper;

import java.util.Date;

import com.course.dto.request.TeacherBankRequestDTO;
import com.course.dto.response.TeacherBankResponseDTO;
import com.course.model.Teacher;
import com.course.model.TeacherBank;

public class TeacherBankMapper {

	public static TeacherBank toEntity(TeacherBankRequestDTO dto) {
		TeacherBank teacherBank = new TeacherBank();

		teacherBank.setAccountHolder(dto.getAccountHolder());
		teacherBank.setAccountNumber(dto.getAccountNumber());
		teacherBank.setBankCode(dto.getBankCode());
		teacherBank.setBankName(dto.getBankName());
		
		
		
		
		
	
		teacherBank.setCreateDate(dto.getCreateDate() != null ? dto.getCreateDate() : new Date());
		teacherBank.setUploadedDate(dto.getUploadedDate() != null ? dto.getUploadedDate() : new Date());
		teacherBank.setDefault(dto.isDefault());

		Teacher teacher = new Teacher();
		teacher.setId(dto.getTeacherId());
		teacherBank.setTeacher(teacher);

		return teacherBank;
	}

	public static TeacherBankResponseDTO toResponse(TeacherBank teacherBank) {
		TeacherBankResponseDTO dto = new TeacherBankResponseDTO();
		dto.setId(teacherBank.getId());
		dto.setAccountHolder(teacherBank.getAccountHolder());

		dto.setAccountNumber(teacherBank.getAccountNumber());
		dto.setBankCode(teacherBank.getBankCode());

		dto.setBankName(teacherBank.getBankName());
		dto.setCreateDate(teacherBank.getCreateDate());

		dto.setDefault(teacherBank.isDefault());
		dto.setUploadedDate(teacherBank.getUploadedDate());

		



		
	
		// getCourse().getTeacher().getId()

		if (teacherBank.getTeacher() != null) {
			
			dto.setTeacherId(teacherBank.getTeacher().getId());
				if (teacherBank.getTeacher().getAccount() != null) {
					dto.setTeacherName(teacherBank.getTeacher().getAccount().getFullname());
				}
			
			
	
//			if (lesson.getCourse().getCourseType() != null) {
//				dto.setId(lesson.getCourse().getCourseType().getId());
//				dto.setCourseTypeName(lesson.getCourse().getCourseType().getName());
//
//			}
//		
//			if (lesson.getCourse().getCommission() != null) {
//				dto.setComissionId(lesson.getCourse().getCommission().getId());
//				dto.setComissionPercent(lesson.getCourse().getCommission().getPercentage());
//			}
		}
		
		return dto;
	}
}
