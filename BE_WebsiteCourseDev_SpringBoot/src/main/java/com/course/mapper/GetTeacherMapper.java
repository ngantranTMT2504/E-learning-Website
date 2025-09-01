package com.course.mapper;

import com.course.dto.request.GetTeacherDTO;
import com.course.model.Account;
import com.course.model.Teacher;

public class GetTeacherMapper {
	public static GetTeacherDTO toBasicDTO(Teacher teacher) {
        Account acc = teacher.getAccount();
        return new GetTeacherDTO(
            acc.getFullname(),
            acc.getEmail(),
            acc.getRegistrationDate(),
            acc.getGender(),
            acc.getBirthday(),
            teacher.getEducationLevel(),
            teacher.getPhone(),
            acc.isStatus()
        );
    }
}
