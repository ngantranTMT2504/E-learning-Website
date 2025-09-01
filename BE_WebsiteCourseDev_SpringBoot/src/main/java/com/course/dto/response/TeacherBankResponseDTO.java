package com.course.dto.response;

import java.util.Date;


import lombok.Data;

@Data
public class TeacherBankResponseDTO {
	
	private int id;
	private String bankCode;
	private String bankName;
	private String accountNumber;
	private String accountHolder;
	
	private Date createDate;
	
	private Date uploadedDate;
	
	private boolean isDefault;
	
	
	    private Integer teacherId;
	    
	    private String teacherName;
	
	

}
