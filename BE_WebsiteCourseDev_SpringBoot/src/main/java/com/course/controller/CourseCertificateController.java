package com.course.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.course.service.iface.CourseCertificateService;

import jakarta.validation.Valid;

import com.course.dto.request.CourseCertificateRequestDTO;
import com.course.dto.response.CourseCertificateResponseDTO;

@RestController
@RequestMapping("/api/course-certificate")
public class CourseCertificateController {

    @Autowired
    private CourseCertificateService courseCertificateService;
    
    @GetMapping
    public ResponseEntity<?> getPagedCourseCertificates(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size) {
        return courseCertificateService.getPagedCourseCertificates(page, size);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseCertificateResponseDTO> getCourseCertificateById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(courseCertificateService.findById(id));
    }

    @PostMapping
    public ResponseEntity<CourseCertificateResponseDTO> createCourseCertificate(@Valid @RequestBody CourseCertificateRequestDTO dto) {
        return ResponseEntity.ok(courseCertificateService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseCertificateResponseDTO> updateCourseCertificate(@PathVariable("id") Integer id,
            @Valid @RequestBody CourseCertificateRequestDTO dto) {
        return ResponseEntity.ok(courseCertificateService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CourseCertificateResponseDTO> deleteCourseCertificate(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(courseCertificateService.deleteById(id));
    }
    
}
