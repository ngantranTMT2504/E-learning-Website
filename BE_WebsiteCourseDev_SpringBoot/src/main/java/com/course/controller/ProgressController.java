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

import com.course.dto.request.ProgressRequestDTO;
import com.course.dto.response.ProgressResponseDTO;
import com.course.service.iface.ProgressService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class ProgressController {
	
	@Autowired
	private ProgressService progressService;

//	@GetMapping({"/progress","/teacher/progress"})
//	public ResponseEntity<List<ProgressResponseDTO>> getAllProgress() {
//	    return ResponseEntity.ok(progressService.findAll());
//	}
	
	@GetMapping({"/progress","/teacher/progress"})
	public ResponseEntity<?> getPagedProgress(
	        @RequestParam(name = "page", defaultValue = "0") int page,
	        @RequestParam(name = "size", defaultValue = "5") int size) {
	    return progressService.getPagedProgress(page, size);
	}

	@GetMapping({"/progress/{id}", "/teacher/progress/{id}"})
	public ResponseEntity<ProgressResponseDTO> getProgressById(@PathVariable("id") Integer id) {
	    return ResponseEntity.ok(progressService.findById(id));
	}

	@PostMapping("/teacher/progress")
	public ResponseEntity<ProgressResponseDTO> createProgress(@Valid @RequestBody ProgressRequestDTO dto) {
	    return ResponseEntity.ok(progressService.create(dto));
	}

	@PutMapping("/teacher/progress/{id}")
	public ResponseEntity<ProgressResponseDTO> updateProgress(@PathVariable("id") Integer id,
	        @Valid @RequestBody ProgressRequestDTO dto) {
	    return ResponseEntity.ok(progressService.update(id, dto));
	}

	@DeleteMapping("/teacher/progress/{id}")
	public ResponseEntity<ProgressResponseDTO> deleteProgress(@PathVariable("id") Integer id) {
	    return ResponseEntity.ok(progressService.deleteById(id));
	}

}
