package com.course.service.impl;

import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.course.dto.request.CourseRequestDTO;
import com.course.dto.response.CourseResponseDTO;
import com.course.exception.ResourceNotFoundException;
import com.course.mapper.CourseMapper;
import com.course.model.Course;
import com.course.repository.CourseRepository;
import com.course.service.iface.CourseFilterService;
import com.course.service.iface.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseRepository courseRepository;
	
	@Override
	public CourseResponseDTO create(CourseRequestDTO courseRequestDTO) {
		Course course = CourseMapper.toEntity(courseRequestDTO);
		Course saved = courseRepository.save(course);
		CourseResponseDTO courseResponseDTO = CourseMapper.toResponse(saved);
		return courseResponseDTO;
	}

	@Override
	public List<CourseResponseDTO> findAll() {
		List<CourseResponseDTO> responseList = courseRepository.findAll().stream().map(CourseMapper::toResponse)
				.sorted(Comparator.comparing(CourseResponseDTO::getTitle).reversed()
						.thenComparing(CourseResponseDTO::getPrice).reversed()
						.thenComparing(CourseResponseDTO::getCreatedDate))
				.collect(Collectors.toList());
		return responseList;
	}

	@Override
	public CourseResponseDTO update(Integer id, CourseRequestDTO courseRequestDTO) {
		findById(id);
		Course updatedCourse = CourseMapper.toEntity(courseRequestDTO);
		updatedCourse.setId(id);
		Course update = courseRepository.save(updatedCourse);
		CourseResponseDTO courseResponseDTO = CourseMapper.toResponse(update);
		return courseResponseDTO;
	}

	@Override
	public CourseResponseDTO findById(Integer id) {
		Course course = courseRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy khoá học"));
		CourseResponseDTO courseResponseDTO = CourseMapper.toResponse(course);
		return courseResponseDTO;
	}

	@Override
	public CourseResponseDTO deleteById(Integer id) {
		CourseResponseDTO courseResponseDTO = findById(id);
		courseRepository.deleteById(id);
		return courseResponseDTO;
	}

	
	@Override
	public ResponseEntity<?> getPagedCoursesByManyParams(
	        String keyword,
	        String title,
	        String slug,
	        Integer minPrice,
	        Integer maxPrice,
	        List<Integer> courseTypeIds,
	        List<Integer> teacherIds,
	        Integer accountId,
	        Double discount,
	        Date createdDate,
	        Date updatedDate,
	        Double averageRating,
	        Boolean status,
	        Pageable pageable
	) {

	    Specification<Course> spec = CourseFilterService.filterSpec(
	            keyword,title,slug, minPrice, maxPrice, courseTypeIds, teacherIds, accountId,
	            discount, createdDate, updatedDate, averageRating, status
	    );

	    Page<Course> pageResult = courseRepository.findAll(spec, pageable);
	    List<CourseResponseDTO> result = pageResult.stream().map(CourseMapper::toResponse).toList();

	    Map<String, Object> response = new HashMap<>();
	    response.put("data", result);
	    response.put("currentPage", pageResult.getNumber());
	    response.put("totalItems", pageResult.getTotalElements());
	    response.put("totalPages", pageResult.getTotalPages());
	    
	    System.out.println("Sort BE nhận: " + pageable.getSort());

	    return ResponseEntity.ok(response);

	}

}
