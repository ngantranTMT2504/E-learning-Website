package com.course.dto.response;

import java.util.Date;

import lombok.Data;

@Data
public class LessonResponseDTO {
	
	private Integer id;
	private String title;
	private String discription;
	private String videoUrl;
	private String exerciseUrl;
	private Integer	lessonOrder;
	private Date postedDate;
	private boolean status;
	
	private Integer courseId;
	private Integer courseTypeId;
	private Integer comissionId;
	private Integer teacherId;
	private Integer accountId;
	
	private double comissionPercent;
	private String teacherName;
	private String courseName;
	private String courseTypeName;
}
