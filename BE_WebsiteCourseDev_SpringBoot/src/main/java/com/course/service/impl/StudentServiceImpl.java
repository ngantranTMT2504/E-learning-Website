package com.course.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.dto.request.GetStudentDTO;
import com.course.dto.request.StudentRequestDTO;
import com.course.dto.response.StudentResponseDTO;
import com.course.mapper.GetStudentMapper;
import com.course.mapper.StudentMapper;
import com.course.model.Account;
import com.course.model.Student;
import com.course.repository.AccountRepository;
import com.course.repository.StudentRepository;
import com.course.service.iface.StudentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private AccountRepository accountRepository;

	@Override
	public Student create(StudentRequestDTO dto) {
		Account account = accountRepository.findById(dto.getAccountId())
				.orElseThrow(() -> new RuntimeException("Không tìm thấy account với id: " + dto.getAccountId()));

		Student student = StudentMapper.toEntity(dto, account);
		return studentRepository.save(student);
	}

	@Override
	public List<GetStudentDTO> getStudentInfo() {
		return studentRepository.findAll().stream().map(GetStudentMapper::toBasicDTO).collect(Collectors.toList());
	}

	@Override
	public List<StudentResponseDTO> findAll() {
		List<Student> students = studentRepository.findAll();
		return StudentMapper.toResponseList(students);
	}

	@Override
	public StudentResponseDTO update(Integer id, StudentRequestDTO dto) {
		Student student = studentRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Không tìm thấy student với id: " + id));

		Account account = accountRepository.findById(dto.getAccountId())
				.orElseThrow(() -> new RuntimeException("Không tìm thấy account với id: " + dto.getAccountId()));

		student.setNote(dto.getNote());
		student.setAccount(account);
		student = studentRepository.save(student);

		return StudentMapper.toResponse(student);
	}

	@Override
	public StudentResponseDTO findById(Integer id) {
		Student student = studentRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Không tìm thấy student với id: " + id));
		return StudentMapper.toResponse(student);
	}

	@Override
	public StudentResponseDTO deleteById(Integer id) {
		Student student = studentRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Không tìm thấy student với id: " + id));
		studentRepository.delete(student);
		return StudentMapper.toResponse(student);
	}

}
