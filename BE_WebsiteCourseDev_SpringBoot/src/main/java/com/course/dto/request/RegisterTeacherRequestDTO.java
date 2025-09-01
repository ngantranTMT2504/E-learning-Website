package com.course.dto.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterTeacherRequestDTO {
	// Account info
    private String email;
    private String password;
    private String fullname;
    
    // Teacher info
    private String degree;
    private String educationLevel;
    private String phone;

    // Bank info
    private String accountHolder;
    private String accountNumber;
    private String bankName;
    private String bankCode;
    
    // Certificate info (có thể nhiều)
    private List<TeacherCertificateRequestDTO> certificates;
}
