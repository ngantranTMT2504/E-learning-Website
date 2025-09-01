package com.course.service.iface;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.course.dto.request.ProgressRequestDTO;
import com.course.dto.response.ProgressResponseDTO;

@Service
public interface ProgressService {
	public ProgressResponseDTO create(ProgressRequestDTO progressRequestDTO);

	public List<ProgressResponseDTO> findAll();

	public ProgressResponseDTO update(Integer id, ProgressRequestDTO progressRequestDTO);

	public ProgressResponseDTO findById(Integer id);

	public ProgressResponseDTO deleteById(Integer id);
	
	public ResponseEntity<?> getPagedProgress(int page, int size);
}
