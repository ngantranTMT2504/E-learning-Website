package com.course.mapper;

import java.util.List;

import com.course.dto.request.StudentRequestDTO;
import com.course.dto.response.StudentResponseDTO;
import com.course.model.Account;
import com.course.model.Student;

public class StudentMapper {
	public static Student toEntity(StudentRequestDTO dto, Account account) {
        Student student = new Student();
        student.setNote(dto.getNote());
        student.setAccount(account);
        return student;
    }

    public static StudentResponseDTO toResponse(Student student) {
        StudentResponseDTO dto = new StudentResponseDTO();
        dto.setId(student.getId());
        dto.setNote(student.getNote());
        dto.setAccountId(student.getAccount().getId());
        dto.setAccountUsername(student.getAccount().getFullname()); // optional
        return dto;
    }

    public static List<StudentResponseDTO> toResponseList(List<Student> students) {
        return students.stream().map(StudentMapper::toResponse).toList();
    }
}
