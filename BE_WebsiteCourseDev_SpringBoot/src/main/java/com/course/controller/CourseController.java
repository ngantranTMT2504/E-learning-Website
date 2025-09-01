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

import com.course.dto.request.CourseRequestDTO;
import com.course.dto.response.CourseResponseDTO;
import com.course.service.iface.CourseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class CourseController {

	@Autowired
	private CourseService courseService;

//	@GetMapping
//	public ResponseEntity<List<CourseResponseDTO>> getAllCourses() {
//		return ResponseEntity.ok(courseService.findAll());
//	}
			
	@GetMapping({"/course/{id}","/teacher/course/{id}"})
	public ResponseEntity<CourseResponseDTO> getCourseById(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(courseService.findById(id));
	}

	@PostMapping("/teacher/course")
	public ResponseEntity<CourseResponseDTO> createCourse(@Valid @RequestBody CourseRequestDTO dto) {
		return ResponseEntity.ok(courseService.create(dto));
	}

	@PutMapping("/teacher/course/{id}")
	public ResponseEntity<CourseResponseDTO> updateCourse(@PathVariable("id") Integer id,
			@Valid @RequestBody CourseRequestDTO dto) {
		return ResponseEntity.ok(courseService.update(id, dto));
	}

	@DeleteMapping("/teacher/course/{id}")
	public ResponseEntity<CourseResponseDTO> deleteCourse(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(courseService.deleteById(id));
	}
	
    @GetMapping({"/course","/teacher/course"})
    public ResponseEntity<?> getFilteredCourses(
            @RequestParam(name = "keyword",required = false) String keyword,
            @RequestParam(name = "title",required = false) String title,
            @RequestParam(name = "slug",required = false) String slug,
            @RequestParam(name = "minPrice",required = false) Integer minPrice,
            @RequestParam(name = "maxPrice",required = false) Integer maxPrice,
            @RequestParam(name = "courseTypeIds",required = false) List<Integer> courseTypeIds,
            @RequestParam(name = "teacherIds",required = false) List<Integer> teacherIds,
            @RequestParam(name = "accountId",required = false) Integer accountId,
            @RequestParam(name = "discount",required = false) Double discount,
            @RequestParam(name = "createdDate",required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date createdDate,
            @RequestParam(name = "updatedDate",required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date updatedDate,
            @RequestParam(name = "averageRating",required = false) Double averageRating,
            @RequestParam(name = "status",required = false) Boolean status,
            Pageable pageable
    ) {
        return courseService.getPagedCoursesByManyParams(
        		keyword, title, slug, minPrice, maxPrice, courseTypeIds, teacherIds, accountId,
                discount, createdDate, updatedDate, averageRating, status, pageable
        );
    }
}
