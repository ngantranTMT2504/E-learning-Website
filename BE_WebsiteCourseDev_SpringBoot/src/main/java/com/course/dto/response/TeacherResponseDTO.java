package com.course.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherResponseDTO {
	private Integer id;
	private String degree;
	private String phone;
	private String educationLevel;
	private boolean status;
	private Integer accountId;
}
