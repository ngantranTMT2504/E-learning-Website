package com.course.service.impl;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.course.dto.request.ProgressRequestDTO;
import com.course.dto.response.ProgressResponseDTO;
import com.course.exception.ResourceNotFoundException;
import com.course.mapper.ProgressMapper;
import com.course.model.Progress;
import com.course.repository.ProgressRepository;
import com.course.service.iface.ProgressService;

@Service
public class ProgressServiceImpl implements ProgressService {

	@Autowired
	private ProgressRepository progressRepository;

	@Override
	public ProgressResponseDTO create(ProgressRequestDTO progressRequestDTO) {
	    Progress progress = ProgressMapper.toEntity(progressRequestDTO);
	    Progress saved = progressRepository.save(progress);
	    ProgressResponseDTO progressResponseDTO = ProgressMapper.toResponse(saved);
	    return progressResponseDTO;
	}

	@Override
	public List<ProgressResponseDTO> findAll() {
	    List<ProgressResponseDTO> responseList = progressRepository.findAll().stream()
	            .map(ProgressMapper::toResponse)
	            .sorted(Comparator.comparing(ProgressResponseDTO::getLessonName).reversed()
	                    .thenComparing(ProgressResponseDTO::getStudentName).reversed())
	            .collect(Collectors.toList());
	    return responseList;
	}

	@Override
	public ProgressResponseDTO update(Integer id, ProgressRequestDTO progressRequestDTO) {
	    findById(id);
	    Progress updatedProgress = ProgressMapper.toEntity(progressRequestDTO);
	    updatedProgress.setId(id);
	    Progress update = progressRepository.save(updatedProgress);
	    ProgressResponseDTO progressResponseDTO = ProgressMapper.toResponse(update);
	    return progressResponseDTO;
	}

	@Override
	public ProgressResponseDTO findById(Integer id) {
	    Progress progress = progressRepository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy tiến độ"));
	    ProgressResponseDTO progressResponseDTO = ProgressMapper.toResponse(progress);
	    return progressResponseDTO;
	}

	@Override
	public ProgressResponseDTO deleteById(Integer id) {
	    ProgressResponseDTO progressResponseDTO = findById(id);
	    progressRepository.deleteById(id);
	    return progressResponseDTO;
	}

	@Override
	public ResponseEntity<?> getPagedProgress(int page, int size) {
	    Pageable pageable = PageRequest.of(page, size, Sort.by("completedDate").descending());
	    Page<Progress> list = progressRepository.findAll(pageable);
	    Page<ProgressResponseDTO> result = list.map(ProgressMapper::toResponse);

	    Map<String, Object> response = new HashMap<>();
	    response.put("data", result.getContent());
	    response.put("currentPage", result.getNumber());
	    response.put("totalItems", result.getTotalElements());
	    response.put("totalPages", result.getTotalPages());

	    return ResponseEntity.ok(response);
	}

}
