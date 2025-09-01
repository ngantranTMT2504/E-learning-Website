package com.course.dto.request;

import java.util.Date;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CourseRegistrationRequestDTO {
	
	private int totalPrice;
	
	private double commission;
	
	private double discount;

	private String transactionCode;
	
	private Date registrationDate;
	
	private boolean status;
	
	@NotNull(message = "studentId không được để trống")
    private Integer studentId;
	
	@NotNull(message = "courseId không được để trống")
    private Integer courseId;
	
	@NotNull(message = "teacherId không được để trống")
    private Integer teacherId;
}
