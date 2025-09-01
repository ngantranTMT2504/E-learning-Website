package com.course.mapper;

import java.util.Date;

import com.course.dto.request.CourseRegistrationRequestDTO;
import com.course.dto.response.CourseRegistrationResponseDTO;
import com.course.model.Course;
import com.course.model.CourseRegistration;
import com.course.model.Student;

public class CourseRegistrationMapper {
	
	public static CourseRegistration toEntity(CourseRegistrationRequestDTO dto) {
		CourseRegistration courseRegistration = new CourseRegistration();
		
		courseRegistration.setTransactionCode(dto.getTransactionCode());
		courseRegistration.setTotalPrice(dto.getTotalPrice());
		courseRegistration.setCommission(dto.getCommission());
		courseRegistration.setDiscount(dto.getDiscount());
		courseRegistration.setRegistrationDate(dto.getRegistrationDate() != null ? dto.getRegistrationDate() : new Date());
		courseRegistration.setStatus(true);
		
		Student student = new Student();
		student.setId(dto.getStudentId());
		courseRegistration.setStudent(student);
		
		Course course = new Course();
		course.setId(dto.getCourseId());
		courseRegistration.setCourse(course);
		
	    return courseRegistration;
	}

    public static CourseRegistrationResponseDTO toResponse(CourseRegistration courseRegistration) {
    	CourseRegistrationResponseDTO dto = new CourseRegistrationResponseDTO();
    	
    	dto.setId(courseRegistration.getId());
    	dto.setTransactionCode(courseRegistration.getTransactionCode());
    	dto.setTotalPrice(courseRegistration.getTotalPrice());
    	dto.setCommission(courseRegistration.getCommission());
    	dto.setDiscount(courseRegistration.getDiscount());
    	dto.setRegistrationDate(courseRegistration.getRegistrationDate());
    	dto.setStatus(courseRegistration.isStatus());
    	
    	if (courseRegistration.getStudent() != null) {
            dto.setStudentId(courseRegistration.getStudent().getId());
            
            if (courseRegistration.getStudent().getAccount() != null) {
                dto.setStudentName(courseRegistration.getStudent().getAccount().getFullname());
            }
        }
    	
    	if (courseRegistration.getCourse() != null) {
             dto.setCourseId(courseRegistration.getCourse().getId());
             dto.setCourseName(courseRegistration.getCourse().getTitle());
        }
    	
    	return dto;
    }
}
