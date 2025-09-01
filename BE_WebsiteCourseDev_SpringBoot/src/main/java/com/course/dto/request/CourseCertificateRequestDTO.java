package com.course.dto.request;

import java.util.Date;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CourseCertificateRequestDTO {
	
	private String certificateUrl;
	
	private Date issuedDate;
	
	@NotNull(message = "studentId không được để trống")
    private Integer studentId;
    
    @NotNull(message = "courseId không được để trống")
    private Integer courseId;
}
