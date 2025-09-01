package com.course.dto.request;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetTeacherDTO {
	private String fullName;
    private String email;
    private Date registrationDate;
    private Boolean gender;
    private Date birthday;
    private String educationLevel;
    private String phone;
    private boolean status;
}
