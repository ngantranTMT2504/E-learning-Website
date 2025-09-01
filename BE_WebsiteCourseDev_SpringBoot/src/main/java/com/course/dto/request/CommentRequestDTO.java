package com.course.dto.request;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequestDTO {
    @NotBlank(message = "Comment không được để trống!")
    private String comment;
	private Integer parentId;
	@Min(value = 0, message = "CountLike phải lớn hơn hoặc bằng 0")
	private int countLike;
	@Min(value = 0, message = "CountDislike phải lớn hơn hoặc bằng 0")
	private int countDislike;
    @NotNull(message = "accountId không được để trống")
	private Integer accountId;
    @NotNull(message = "lessonId không được để trống")
	private Integer lessonId;
}
