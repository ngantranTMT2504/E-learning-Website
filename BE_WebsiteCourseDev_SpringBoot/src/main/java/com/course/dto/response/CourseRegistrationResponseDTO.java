package com.course.dto.response;

import java.util.Date;

import lombok.Data;

@Data
public class CourseRegistrationResponseDTO {
	
	private Integer id;
	private int totalPrice;
	private double commission;
	private double discount;
	private String transactionCode;
	private Date registrationDate;
	private boolean status;
	
	private Integer courseId;
	private Integer studentId;
	
	private String courseName;
	private String studentName;
}
