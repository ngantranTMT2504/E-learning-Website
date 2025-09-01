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

import com.course.dto.request.TeacherCertificateRequestDTO;
import com.course.dto.response.TeacherCertificateResponseDTO;
import com.course.service.iface.TeacherCertificateService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/teacher-certificate")
public class TeacherCertificateController {

	@Autowired
	private TeacherCertificateService teacherCertificateService;

//	@GetMapping
//	public ResponseEntity<List<TeacherCertificateResponseDTO>> getAllLesson() {
//		return ResponseEntity.ok(teacherCertificateService.findAll());
//	}

	@GetMapping("/{id}")
	public ResponseEntity<TeacherCertificateResponseDTO> getTeacherCertificateById(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(teacherCertificateService.findById(id));
	}
	@PostMapping
	public ResponseEntity<TeacherCertificateResponseDTO> createTeacherCertificate(@Valid @RequestBody TeacherCertificateRequestDTO dto) {
		return ResponseEntity.ok(teacherCertificateService.create(dto));
	}

	@PutMapping("/{id}")
	public ResponseEntity<TeacherCertificateResponseDTO> updateTeacherCertificate(@PathVariable("id") Integer id,
			@Valid @RequestBody TeacherCertificateRequestDTO dto) {
		return ResponseEntity.ok(teacherCertificateService.update(id, dto));
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<TeacherCertificateResponseDTO> deleteTeacherCertificate(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(teacherCertificateService.deleteById(id));
	}
//	
	@GetMapping()
    public ResponseEntity<?> getFilteredTeacherCertificate(
    		@RequestParam(name = "certificateName",required = false) String certificateName,
            @RequestParam(name = "certificateImage",required = false) String certificateImage,
            @RequestParam(name = "issuingOrganization",required = false) String issuingOrganization,
            @RequestParam(name = "issuedYear",required = false) Integer issuedYear,
            @RequestParam(name = "teacherId",required = false) List<Integer> teacherId,
            @RequestParam(name = "uploadedDate",required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date uploadedDate,
            @RequestParam(name = "accountId", required = false) Integer accountId,

            Pageable pageable
    ) {
        return teacherCertificateService.getPagedTeacherCertificateByManyParams(certificateName,certificateImage,issuedYear,issuingOrganization, uploadedDate,  teacherId,
        		accountId,	 pageable
        );
    } 
}
