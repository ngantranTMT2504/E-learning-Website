package com.course.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponseDTO {
	private Integer id;
    private String note;
    private Integer accountId;
    private String accountUsername;
}
