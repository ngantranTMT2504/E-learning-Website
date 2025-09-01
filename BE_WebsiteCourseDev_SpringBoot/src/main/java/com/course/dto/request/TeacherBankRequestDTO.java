package com.course.dto.request;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TeacherBankRequestDTO {
	
	
	@NotBlank(message = "Mã ngân hàng chứng nhận không được để trống")
	private String bankCode;
	@NotBlank(message = "Tên ngân hàng chứng nhận không được để trống")
	private String bankName;
	@NotBlank(message = "Số tài khoản không được để trống")
	private String accountNumber;
	@NotBlank(message = "Chủ sở hữu không được để trống")
	private String accountHolder;
	
	private Date createDate;
	
	private Date uploadedDate;
	
	private boolean isDefault;
	
	 @NotNull(message = "teacherId không được để trống")
	    private Integer teacherId;
	
	
	   
	

}
