package com.course.dto.response;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentResponseDTO {
    private Integer id;
    private String comment;
	private Integer parentId;
	private int countLike;
	private int countDislike;
    private Date createdDate;
	private Integer accountId;
    private String email;
    private String accountAvarta;
	private Integer lessonId;
    private List<CommentResponseDTO> children;
}
