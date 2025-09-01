package com.course.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleRequestDTO {
	@NotBlank(message = "Role name không được để trống")
    @Size(max = 100, message = "Role name không được vượt quá 100 ký tự")
    private String name;
	
}
