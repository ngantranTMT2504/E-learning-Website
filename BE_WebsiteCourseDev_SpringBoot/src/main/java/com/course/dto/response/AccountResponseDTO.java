package com.course.dto.response;

import java.util.Date;

import com.course.model.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponseDTO {
    private Integer id;
    private String email;
    private String fullname;
    private String password;
    private Boolean gender;
    private Date birthday;
    private String avatar;
    private Role role;
    private Boolean status;
    private Date registeredDate;
}