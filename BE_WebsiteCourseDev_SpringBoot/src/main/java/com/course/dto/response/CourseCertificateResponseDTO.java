package com.course.dto.response;

import java.util.Date;

import lombok.Data;

@Data
public class CourseCertificateResponseDTO {
	
	private Integer id;
	private String certificateUrl;
	private Date issuedDate;
	
	private Integer studentId;
	private Integer courseId;
	
	private String studentName;
	private String courseName;
}
