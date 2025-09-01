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

import com.course.dto.request.LessonRequestDTO;
import com.course.dto.response.LessonResponseDTO;
import com.course.service.iface.LessonService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/lesson")
public class LessonController {

	@Autowired
	private LessonService lessonService;

//	@GetMapping
//	public ResponseEntity<List<LessonResponseDTO>> getAllLesson() {
//		return ResponseEntity.ok(lessonService.findAll());
//	}

	@GetMapping("/{id}")
	public ResponseEntity<LessonResponseDTO> getLessonById(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(lessonService.findById(id));
	}
	@PostMapping
	public ResponseEntity<LessonResponseDTO> createLesson(@Valid @RequestBody LessonRequestDTO dto) {
		return ResponseEntity.ok(lessonService.create(dto));
	}

	@PutMapping("/{id}")
	public ResponseEntity<LessonResponseDTO> updateLesson(@PathVariable("id") Integer id,
			@Valid @RequestBody LessonRequestDTO dto) {
		return ResponseEntity.ok(lessonService.update(id, dto));
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<LessonResponseDTO> deleteLesson(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(lessonService.deleteById(id));
	}
	
    @GetMapping
    public ResponseEntity<?> getFilteredLessons(
            @RequestParam(name = "title",required = false) String title,
            @RequestParam(name = "courseName",required = false) String courseName,
            @RequestParam(name = "lessonOrder",required = false) Integer lessonOrder,
            @RequestParam(name = "courseIds",required = false) List<Integer> courseIds,
            @RequestParam(name = "teacherIds",required = false) List<Integer> teacherIds,
            @RequestParam(name = "accountId",required = false) Integer accountId,
            @RequestParam(name = "postedDate",required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date postedDate,
            @RequestParam(name = "status",required = false) Boolean status,
            Pageable pageable
    ) {
        return lessonService.getPagedLessonsByManyParams(
                postedDate, lessonOrder, title, courseName, status, courseIds, teacherIds, accountId, pageable
        );
   }
}
