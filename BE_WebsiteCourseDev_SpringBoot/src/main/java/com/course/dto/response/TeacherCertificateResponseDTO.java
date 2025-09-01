package com.course.dto.response;

import java.util.Date;

import lombok.Data;

@Data
public class TeacherCertificateResponseDTO {
	
	private Integer id;
	private String certificateName;
	private String certificateImage;
	private Date uploadedDate;
	private Integer issuedYear;
	private String issuingOrganization;
	
	private Integer teacherId;
	private Integer accountId;

	private String teacherName;
}
