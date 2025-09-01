package com.course.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.course.dto.request.TeacherBankRequestDTO;
import com.course.dto.response.TeacherBankResponseDTO;
import com.course.service.iface.TeacherBankService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/teacher-bank")
public class TeacherBankController {

	@Autowired
	private TeacherBankService teacherBankService;

//	@GetMapping
//	public ResponseEntity<List<TeacherBankResponseDTO>> getAllLesson() {
//		return ResponseEntity.ok(teacherBankService.findAll());
//	}

	@GetMapping("/{id}")
	public ResponseEntity<TeacherBankResponseDTO> getTeacherBankById(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(teacherBankService.findById(id));
	}

	@PostMapping
	public ResponseEntity<TeacherBankResponseDTO> createTeacherBank(
			@Valid @RequestBody TeacherBankRequestDTO dto) {
		return ResponseEntity.ok(teacherBankService.create(dto));
	}

	@PutMapping("/{id}")
	public ResponseEntity<TeacherBankResponseDTO> updateTeacherBank(@PathVariable("id") Integer id,
			@Valid @RequestBody TeacherBankRequestDTO dto) {
		return ResponseEntity.ok(teacherBankService.update(id, dto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<TeacherBankResponseDTO> deleteTeacherBank(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(teacherBankService.deleteById(id));
	}

	@GetMapping()
    public ResponseEntity<?> getFilteredTeacherBank(
    		@RequestParam(name = "bankCode",required = false) String bankCode,
            @RequestParam(name = "bankName",required = false) String bankName,
            @RequestParam(name = "accountNumber",required = false) Integer accountNumber,
            @RequestParam(name = "accountHolder",required = false) Integer accountHolder,
            @RequestParam(name = "teacherId",required = false) List<Integer> teacherId,
            @RequestParam(name = "createDate",required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date createDate,
            @RequestParam(name = "uploadedDate",required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date uploadedDate,
            @RequestParam(name = "isDefault",required = false) Boolean isDefault,
            Pageable pageable
    ) {
        return teacherBankService.getPagedTeacherBankByManyParams(bankCode,bankName,accountNumber,
    			accountHolder, createDate, uploadedDate, isDefault,  teacherId,
    			 pageable
        );
    } 
}
