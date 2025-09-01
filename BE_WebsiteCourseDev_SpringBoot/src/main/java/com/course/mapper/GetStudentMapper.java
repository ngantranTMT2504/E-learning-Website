package com.course.mapper;

import com.course.dto.request.GetStudentDTO;
import com.course.model.Account;
import com.course.model.Student;

public class GetStudentMapper {
	public static GetStudentDTO toBasicDTO(Student student) {
        Account acc = student.getAccount();
        return new  GetStudentDTO(
            acc.getFullname(),
            acc.getEmail(),
            acc.getRegistrationDate(),
            acc.getGender(),
            acc.getBirthday(),
            acc.isStatus()
        );
    }
}
