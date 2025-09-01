package com.course.dto.response;

import java.util.Date;

import lombok.Data;

@Data
public class ProgressResponseDTO {
	
	private Integer id;
	private Date completedDate;
	private Integer lessonId;
	private Integer studentId;
	private Integer courseId;
	private String lessonName;
	private String studentName;
}
