package com.course.mapper;

import java.util.Date;

import com.course.dto.request.ProgressRequestDTO;
import com.course.dto.response.ProgressResponseDTO;
import com.course.model.Lesson;
import com.course.model.Progress;
import com.course.model.Student;

public class ProgressMapper {
	
	public static Progress toEntity(ProgressRequestDTO dto) {
	    Progress progress = new Progress();
	    
	    progress.setCompletedDate(dto.getCompletedDate() != null ? dto.getCompletedDate() : new Date());
	    
	    Student student = new Student();
	    student.setId(dto.getStudentId());
	    progress.setStudent(student);

	    Lesson lesson = new Lesson();
	    lesson.setId(dto.getLessonId());
	    progress.setLesson(lesson);
	    
	    return progress;
	}

	public static ProgressResponseDTO toResponse(Progress progress) {
	    ProgressResponseDTO dto = new ProgressResponseDTO();
	    
	    dto.setId(progress.getId());
	    dto.setCompletedDate(progress.getCompletedDate());
	    
	    if(progress.getStudent() != null) {
	        dto.setStudentId(progress.getStudent().getId());
	        
	        if(progress.getStudent().getAccount() != null) {
	            dto.setStudentName(progress.getStudent().getAccount().getFullname());
	        }
	    }
	    
	    if(progress.getLesson() != null) {
	        dto.setLessonId(progress.getLesson().getId());
	        dto.setLessonName(progress.getLesson().getTitle());
	    }
	    
	    return dto;
	}

}
