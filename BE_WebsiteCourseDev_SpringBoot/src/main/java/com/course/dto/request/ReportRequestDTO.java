package com.course.dto.request;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportRequestDTO {
    @NotBlank(message = "reason is required")
	private String reason;
    @NotBlank(message = "description is required")
	private String discription;
	private Date reportDate = new Date();
    @NotNull(message = "lessonId is required")
	private Integer lessonId;
    @NotNull(message = "studentId is required")
	private Integer studentId;
}
