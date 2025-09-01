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

import com.course.dto.request.CommissionRequestDTO;
import com.course.dto.response.CommissionResponseDTO;
import com.course.service.iface.CommissionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/commission")
public class CommissionController {

	@Autowired
	private CommissionService commissionService;

//	@GetMapping
//	public ResponseEntity<List<CommissionResponseDTO>> getAllCommission() {
//		return ResponseEntity.ok(commissionService.findAll());
//	}

	@GetMapping("/{id}")
	public ResponseEntity<CommissionResponseDTO> getCommissionById(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(commissionService.findById(id));
	}

	@PostMapping
	public ResponseEntity<CommissionResponseDTO> createCommission(@Valid @RequestBody CommissionRequestDTO dto) {
		return ResponseEntity.ok(commissionService.create(dto));
	}

	@PutMapping("/{id}")
	public ResponseEntity<CommissionResponseDTO> updateCommission(@PathVariable("id") Integer id,
			@Valid @RequestBody CommissionRequestDTO dto) {
		return ResponseEntity.ok(commissionService.update(id, dto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<CommissionResponseDTO> deleteCommission(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(commissionService.deleteById(id));
	}
	
	@GetMapping
	public ResponseEntity<?> getFilteredCourseType(
	    @RequestParam(name = "percentage", required = false) Double percentage,
	    @RequestParam(name = "applicableDate", required = false) Date applicableDate,
	    Pageable pageable
	) {
	    return commissionService.getPagedCommissionByManyParams(percentage, applicableDate, pageable);
	}

}
