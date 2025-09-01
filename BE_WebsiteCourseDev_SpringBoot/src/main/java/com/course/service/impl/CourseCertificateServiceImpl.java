package com.course.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Comparator;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.course.dto.request.CourseCertificateRequestDTO;
import com.course.dto.response.CourseCertificateResponseDTO;
import com.course.exception.ResourceNotFoundException;
import com.course.mapper.CourseCertificateMapper;
import com.course.model.CourseCertificate;
import com.course.repository.CourseCertificateRepository;
import com.course.service.iface.CourseCertificateService;

@Service
public class CourseCertificateServiceImpl implements CourseCertificateService {

    @Autowired
    private CourseCertificateRepository courseCertificateRepository;

    @Override
    public CourseCertificateResponseDTO create(CourseCertificateRequestDTO courseCertificateRequestDTO) {
        CourseCertificate courseCertificate = CourseCertificateMapper.toEntity(courseCertificateRequestDTO);
        CourseCertificate saved = courseCertificateRepository.save(courseCertificate);
        CourseCertificateResponseDTO responseDTO = CourseCertificateMapper.toResponse(saved);
        return responseDTO;
    }

    @Override
    public List<CourseCertificateResponseDTO> findAll() {
        List<CourseCertificateResponseDTO> responseList = courseCertificateRepository.findAll().stream()
            .map(CourseCertificateMapper::toResponse)
            .sorted(Comparator.comparing(CourseCertificateResponseDTO::getIssuedDate).reversed())
            .collect(Collectors.toList());
        return responseList;
    }

    @Override
    public CourseCertificateResponseDTO update(Integer id, CourseCertificateRequestDTO courseCertificateRequestDTO) {
        findById(id);
        CourseCertificate updatedCourseCertificate = CourseCertificateMapper.toEntity(courseCertificateRequestDTO);
        updatedCourseCertificate.setId(id);
        CourseCertificate update = courseCertificateRepository.save(updatedCourseCertificate);
        CourseCertificateResponseDTO responseDTO = CourseCertificateMapper.toResponse(update);
        return responseDTO;
    }

    @Override
    public CourseCertificateResponseDTO findById(Integer id) {
        CourseCertificate courseCertificate = courseCertificateRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy chứng nhận"));
        CourseCertificateResponseDTO responseDTO = CourseCertificateMapper.toResponse(courseCertificate);
        return responseDTO;
    }

    @Override
    public CourseCertificateResponseDTO deleteById(Integer id) {
        CourseCertificateResponseDTO responseDTO = findById(id);
        courseCertificateRepository.deleteById(id);
        return responseDTO;
    }
    
    @Override
    public ResponseEntity<?> getPagedCourseCertificates(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("issuedDate").descending());
        Page<CourseCertificate> list = courseCertificateRepository.findAll(pageable);
	    Page<CourseCertificateResponseDTO> result = list.map(CourseCertificateMapper::toResponse);

        Map<String, Object> response = new HashMap<>();
        response.put("data", result.getContent());
        response.put("currentPage", result.getNumber());
        response.put("totalItems", result.getTotalElements());
        response.put("totalPages", result.getTotalPages());

        return ResponseEntity.ok(response);
    }

}
