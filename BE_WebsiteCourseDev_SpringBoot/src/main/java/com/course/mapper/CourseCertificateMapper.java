package com.course.mapper;

import java.util.Date;

import com.course.dto.request.CourseCertificateRequestDTO;
import com.course.dto.response.CourseCertificateResponseDTO;
import com.course.model.CourseCertificate;
import com.course.model.Course;
import com.course.model.Student;

public class CourseCertificateMapper {

	public static CourseCertificate toEntity(CourseCertificateRequestDTO dto) {
		CourseCertificate courseCertificate = new CourseCertificate();
		
		courseCertificate.setCertificateUrl(dto.getCertificateUrl());
		courseCertificate.setIssuedDate(dto.getIssuedDate() != null ? dto.getIssuedDate() : new Date());
		
		Student student = new Student();
		student.setId(dto.getStudentId());
	    courseCertificate.setStudent(student);

	    Course course = new Course();
	    course.setId(dto.getCourseId());
	    courseCertificate.setCourse(course);
		
		return courseCertificate;
	}
	
	public static CourseCertificateResponseDTO toResponse(CourseCertificate courseCertificate) {
		CourseCertificateResponseDTO dto = new CourseCertificateResponseDTO();
		
		dto.setId(courseCertificate.getId());
		dto.setCertificateUrl(courseCertificate.getCertificateUrl());
		dto.setIssuedDate(courseCertificate.getIssuedDate());
		
		if(courseCertificate.getStudent() != null) {
			dto.setStudentId(courseCertificate.getStudent().getId());
			
			if(courseCertificate.getStudent().getAccount() != null) {
				dto.setStudentName(courseCertificate.getStudent().getAccount().getFullname());
			}
		}
		
		if(courseCertificate.getCourse() != null) {
			dto.setCourseId(courseCertificate.getCourse().getId());
			dto.setCourseName(courseCertificate.getCourse().getTitle());
		}
		
		return dto;
	}
}
