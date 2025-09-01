package com.course.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CourseTypeRequestDTO {

	   @NotBlank(message = "Tên loại khóa học không được để trống")
	private String name;
	   
	   private boolean status;

}
