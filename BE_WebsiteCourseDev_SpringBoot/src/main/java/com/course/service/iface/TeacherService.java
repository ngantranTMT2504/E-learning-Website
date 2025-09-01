package com.course.service.iface;

import java.util.List;

import com.course.dto.request.GetTeacherDTO;
import com.course.dto.request.TeacherRequestDTO;
import com.course.dto.response.TeacherResponseDTO;
import com.course.model.Teacher;

public interface TeacherService {
	public Teacher create(TeacherRequestDTO account);

    public List<TeacherResponseDTO> findAll();

    public TeacherResponseDTO update(Integer id, TeacherRequestDTO account);

    public TeacherResponseDTO findById(Integer id);

    public TeacherResponseDTO deleteById(Integer id);
    
    public List<GetTeacherDTO> getTeacherInfo();
}
