package com.course.service.iface;

import java.util.List;

import com.course.dto.request.GetStudentDTO;
import com.course.dto.request.StudentRequestDTO;
import com.course.dto.response.StudentResponseDTO;
import com.course.model.Student;

public interface StudentService {
	Student create(StudentRequestDTO dto);

	List<StudentResponseDTO> findAll();

	StudentResponseDTO update(Integer id, StudentRequestDTO dto);

	StudentResponseDTO findById(Integer id);

	StudentResponseDTO deleteById(Integer id);

	List<GetStudentDTO> getStudentInfo();
}
