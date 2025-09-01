package com.course.mapper;

import java.util.Date;


import com.course.dto.request.TeacherCertificateRequestDTO;
import com.course.dto.response.TeacherCertificateResponseDTO;
import com.course.model.Account;
import com.course.model.Teacher;
import com.course.model.TeacherCertificate;

public class TeacherCertificateMapper {

	public static TeacherCertificate toEntity(TeacherCertificateRequestDTO dto) {
		TeacherCertificate teacherCertificate = new TeacherCertificate();

		teacherCertificate.setCertificateName(dto.getCertificateName());
		teacherCertificate.setCertificateImage(dto.getCertificateImage());
		teacherCertificate.setIssuedYear(dto.getIssuedYear());
		teacherCertificate.setIssuingOrganization(dto.getIssuingOrganization());
		teacherCertificate.setUploadedDate(dto.getUploadedDate() != null ? dto.getUploadedDate() : new Date());

		Teacher teacher = new Teacher();
		teacher.setId(dto.getTeacherId());
		teacherCertificate.setTeacher(teacher);

		 
		

		return teacherCertificate;
	}

	public static TeacherCertificateResponseDTO toResponse(TeacherCertificate teacherCertificate) {
		TeacherCertificateResponseDTO dto = new TeacherCertificateResponseDTO();
		dto.setId(teacherCertificate.getId());
		dto.setCertificateName(teacherCertificate.getCertificateName());

		dto.setCertificateImage(teacherCertificate.getCertificateImage());

		dto.setUploadedDate(teacherCertificate.getUploadedDate());
		dto.setIssuedYear(teacherCertificate.getIssuedYear());
		dto.setIssuingOrganization(teacherCertificate.getIssuingOrganization());
		// getCourse().getTeacher().getId()

		  if (teacherCertificate.getTeacher() != null) {
		        dto.setTeacherId(teacherCertificate.getTeacher().getId());

		        if (teacherCertificate.getTeacher().getAccount() != null) {
		            dto.setTeacherName(teacherCertificate.getTeacher().getAccount().getFullname());
		            dto.setAccountId(teacherCertificate.getTeacher().getAccount().getId());
		        }
		    }

		return dto;
	}
}
