package com.course.dto.response;


import com.course.model.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponseDTO {
    private String email;
    private String fullname;
    private String password;
    private Boolean status;
    private Role role;
}