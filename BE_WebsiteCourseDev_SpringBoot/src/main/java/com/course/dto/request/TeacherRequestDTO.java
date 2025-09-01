package com.course.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherRequestDTO {
	@NotBlank(message = "Bằng tốt nghiệp không được để trống")
	private String degree;
	
	@NotBlank(message = "Số điện thoại không được để trống")
	@Pattern(regexp = "^(0[0-9]{9})$", message = "Số điện thoại phải bắt đầu bằng số 0 và có 10 chữ số")
	private String phone;
	
	@NotBlank(message = "Education level không được để trống")
	private String educationLevel;
	
	private boolean status;
}
