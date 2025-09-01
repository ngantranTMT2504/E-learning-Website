package com.course.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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

import com.course.dto.request.ReportRequestDTO;
import com.course.dto.response.ReportResponseDTO;
import com.course.payload.PaginationResponse;
import com.course.service.iface.ReportService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/reports")
public class ReportController {
    @Autowired
    private ReportService reportService;

    @GetMapping("/admin")
    public ResponseEntity<?> findAll(
            @RequestParam(name = "keyword", required = false) String keyword,
            @RequestParam(name = "lessonId", required = false) Integer lessonId,
            @RequestParam(name = "studentId", required = false) Integer studentId,
            @RequestParam(name = "startDate", required = false) Date startDate,
            @RequestParam(name = "endDate", required = false) Date endDate,
            Pageable pageable

    ){
        PaginationResponse<ReportResponseDTO> response = reportService.findAll(pageable, keyword, lessonId, studentId, startDate, endDate);
        return ResponseEntity.ok().body(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        ReportResponseDTO response = reportService.findById(id);
        return ResponseEntity.ok().body(response);
    }
    @PostMapping
    public ResponseEntity<?> createReport(@RequestBody @Valid ReportRequestDTO dto) {
        ReportResponseDTO response = reportService.create(dto);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateReport(@PathVariable Integer id, @RequestBody @Valid ReportRequestDTO requestDTO) {
        ReportResponseDTO response = reportService.update(id, requestDTO);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReport(@PathVariable Integer id) {
        ReportResponseDTO response = reportService.delete(id);
        return ResponseEntity.ok().body(response);
    }
}
