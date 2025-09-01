package com.course.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RatingRequestDTO {
    @Min(value = 1, message = "rating phải nhỏ nhất là 1")
    @Max(value = 5, message = "Rating lớn nhất là 5 5")
	private int rating;
    @NotBlank(message = "Content là bắt buộc")
	private String content;
    @NotNull(message = "Lesson ID là bắt buộc")
	private Integer lessonId;
    @NotNull(message = "Student ID là bắt buộc")
	private Integer studentId;
}
