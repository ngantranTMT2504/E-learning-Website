package com.course.dto.request;

import java.util.Date;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProgressRequestDTO {
	
	private Date completedDate;
	
	@NotNull(message = "studentId không được để trống")
    private Integer studentId;
	
	@NotNull(message = "lessonId không được để trống")
    private Integer lessonId;

}
