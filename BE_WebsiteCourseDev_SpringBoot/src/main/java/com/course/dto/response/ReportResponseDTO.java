package com.course.dto.response;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReportResponseDTO {
	private Integer id;
	private String reason;
	private String discription;
	private Date reportDate;
	private Integer lessonId;
	private String lessonName;
	private Integer studentId;
	private String nameStudent;
	private String emailStudent;
}
