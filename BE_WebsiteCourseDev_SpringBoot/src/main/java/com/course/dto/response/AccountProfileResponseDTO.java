package com.course.dto.response;

import java.util.Date;

import lombok.Data;

@Data
public class AccountProfileResponseDTO {
		private Integer id;
	    private String email;
	    private String fullname;
	    private Boolean gender;
	    private Date birthday;
	    private String avatar;
	    private Boolean status;
	    private Date registeredDate;
}
