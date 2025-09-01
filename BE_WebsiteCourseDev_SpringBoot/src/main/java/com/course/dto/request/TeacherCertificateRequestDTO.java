package com.course.dto.request;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TeacherCertificateRequestDTO {
	@NotBlank(message = "Tên chứng nhận không được để trống")
	private String certificateName;
	@NotNull(message = "Ảnh chứng nhận không được để trống")
	private String certificateImage;
	private Date uploadedDate;
	private Integer issuedYear;
	private String issuingOrganization;
	 @NotNull(message = "teacherId không được để trống")
	    private Integer teacherId;
	
}
