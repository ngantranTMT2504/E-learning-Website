package com.course.dto.request;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateProfileRequestDTO {
	
	private String email;
	
	@NotBlank(message = "Họ tên không được để trống")
    @Size(max = 100, message = "Họ tên không được vượt quá 100 ký tự")
    private String fullname;

    private Boolean gender;

    private Date birthday;

    @Size(max = 250, message = "Đường dẫn ảnh đại diện không được vượt quá 250 ký tự")
    private String avatar;
}
