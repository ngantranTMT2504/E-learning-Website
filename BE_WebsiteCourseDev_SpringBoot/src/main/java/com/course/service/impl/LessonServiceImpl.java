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

import com.course.dto.request.LessonRequestDTO;
import com.course.dto.response.LessonResponseDTO;
import com.course.exception.ResourceNotFoundException;
import com.course.mapper.LessonMapper;
import com.course.model.Lesson;
import com.course.repository.LessonRepository;
import com.course.service.iface.LessonFilterService;
import com.course.service.iface.LessonService;

@Service
public class LessonServiceImpl implements LessonService {

	@Autowired
	private LessonRepository lessonRepository;

	@Override
	public LessonResponseDTO create(LessonRequestDTO lessonRequestDTO) {
		Lesson lesson = LessonMapper.toEntity(lessonRequestDTO);
		Lesson saved = lessonRepository.save(lesson);
		LessonResponseDTO lessonResponseDTO = LessonMapper.toResponse(saved);
		return lessonResponseDTO;
	}

	@Override
	public List<LessonResponseDTO> findAll() {
		List<LessonResponseDTO> responseList = lessonRepository.findAll().stream().map(LessonMapper::toResponse)
				.sorted(Comparator.comparing(LessonResponseDTO::getTitle).reversed()
						.thenComparing(LessonResponseDTO::getComissionPercent).reversed()
						.thenComparing(LessonResponseDTO::getPostedDate))
				.collect(Collectors.toList());
		return responseList;
	}

	@Override
	public LessonResponseDTO update(Integer id, LessonRequestDTO lessonRequestDTO) {
		findById(id);
		Lesson updatedLesson = LessonMapper.toEntity(lessonRequestDTO);
		updatedLesson.setId(id);
		Lesson update = lessonRepository.save(updatedLesson);
		LessonResponseDTO lessonResponseDTO = LessonMapper.toResponse(update);
		return lessonResponseDTO;
	}

	@Override
	public LessonResponseDTO findById(Integer id) {
		Lesson lesson = lessonRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy bài học"));
		LessonResponseDTO lessonResponseDTO = LessonMapper.toResponse(lesson);
		return lessonResponseDTO;
	}

	@Override
	public LessonResponseDTO deleteById(Integer id) {
		LessonResponseDTO lessonResponseDTO = findById(id);
		lessonRepository.deleteById(id);
		return lessonResponseDTO;
	}

	@Override
	public ResponseEntity<?> getPagedLessonsByManyParams(Date postedDate, Integer lessonOrder, String title,
			String courseName, Boolean status, List<Integer> courseIds, List<Integer> teacherIds, Integer accountId, Pageable pageable

	) {
		Specification<Lesson> spec = LessonFilterService.filterSpec(postedDate, lessonOrder, title, courseName, status,
				courseIds, teacherIds,accountId);

		Page<Lesson> pageResult = lessonRepository.findAll(spec, pageable);
		List<LessonResponseDTO> result = pageResult.stream().map(LessonMapper::toResponse).toList();

		Map<String, Object> response = new HashMap<>();
		response.put("data", result);
		response.put("currentPage", pageResult.getNumber());
		response.put("totalItems", pageResult.getTotalElements());
		response.put("totalPages", pageResult.getTotalPages());

		return ResponseEntity.ok(response);
	}

}
