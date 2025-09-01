package com.course.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.dto.request.GetTeacherDTO;
import com.course.dto.request.TeacherRequestDTO;
import com.course.dto.response.TeacherResponseDTO;
import com.course.mapper.GetTeacherMapper;
import com.course.mapper.TeacherMapper;
import com.course.model.Teacher;
import com.course.repository.TeacherRepository;
import com.course.service.iface.TeacherService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
	@Autowired
	private TeacherRepository teacherRepository;

	@Override
	public Teacher create(TeacherRequestDTO dto) {
		Teacher teacher = TeacherMapper.toEntity(dto);
		// Nếu cần gán account, thêm ở đây: teacher.setAccount(account);
		return teacherRepository.save(teacher);
	}

	@Override
	public List<GetTeacherDTO> getTeacherInfo() {
		return teacherRepository.findAll().stream().map(GetTeacherMapper::toBasicDTO).collect(Collectors.toList());
	}

	@Override
	public List<TeacherResponseDTO> findAll() {
		List<Teacher> teachers = teacherRepository.findAll();
		return TeacherMapper.toResponseDTOList(teachers);
	}

	@Override
	public TeacherResponseDTO update(Integer id, TeacherRequestDTO dto) {
		Optional<Teacher> optional = teacherRepository.findById(id);
		if (!optional.isPresent()) {
			throw new RuntimeException("Không tìm thấy giáo viên có ID: " + id);
		}

		Teacher teacher = optional.get();
		teacher.setDegree(dto.getDegree());
		teacher.setPhone(dto.getPhone());
		teacher.setEducationLevel(dto.getEducationLevel());
		teacher.setStatus(dto.isStatus());

		teacher = teacherRepository.save(teacher);
		return TeacherMapper.toResponse(teacher);
	}

	@Override
	public TeacherResponseDTO findById(Integer id) {
		Teacher teacher = teacherRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Không tìm thấy giáo viên có ID: " + id));
		return TeacherMapper.toResponse(teacher);
	}

	@Override
	public TeacherResponseDTO deleteById(Integer id) {
		Teacher teacher = teacherRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Không tìm thấy giáo viên có ID: " + id));

		teacherRepository.delete(teacher);
		return TeacherMapper.toResponse(teacher);
	}

}
