package com.course.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.course.service.iface.CourseRegistrationService;

import jakarta.validation.Valid;

import com.course.dto.request.CourseRegistrationRequestDTO;
import com.course.dto.response.CourseRegistrationResponseDTO;


@RestController
@RequestMapping("/api/course-registration")
public class CourseRegistrationController {

    @Autowired
    private CourseRegistrationService courseRegistrationService;

    @GetMapping("/{id}")
    public ResponseEntity<CourseRegistrationResponseDTO> getCourseRegistrationById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(courseRegistrationService.findById(id));
    }

    @PostMapping
    public ResponseEntity<CourseRegistrationResponseDTO> createCourseRegistration(@Valid @RequestBody CourseRegistrationRequestDTO dto) {
        return ResponseEntity.ok(courseRegistrationService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseRegistrationResponseDTO> updateCourseRegistration(@PathVariable("id") Integer id,
            @Valid @RequestBody CourseRegistrationRequestDTO dto) {
        return ResponseEntity.ok(courseRegistrationService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CourseRegistrationResponseDTO> deleteCourseRegistration(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(courseRegistrationService.deleteById(id));
    }
    
    @GetMapping
    public ResponseEntity<?> getFilteredCourseRegistrations(
            @RequestParam(name = "page",defaultValue = "0") int page,
            @RequestParam(name = "size",defaultValue = "5") int size,
            @RequestParam(name = "transactionCode",required = false) String transactionCode,
            @RequestParam(name = "minPrice",required = false) Integer minPrice,
            @RequestParam(name = "maxPrice",required = false) Integer maxPrice,
            @RequestParam(name = "courseIds",required = false) List<Integer> courseIds,
            @RequestParam(name = "studentIds",required = false) List<Integer> studentIds,
            @RequestParam(name = "discount",required = false) Double discount,
            @RequestParam(name = "commission",required = false) Double commission,
            @RequestParam(name = "registrationDate",required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date registrationDate,
            @RequestParam(name = "status",required = false) Boolean status
    ) {
        return ResponseEntity.ok(courseRegistrationService.getPagedCourseRegistrationsByManyParams(
                page, size, transactionCode, minPrice, maxPrice, commission, discount, 
	    		registrationDate, status, studentIds, courseIds
        ));
    }
}

