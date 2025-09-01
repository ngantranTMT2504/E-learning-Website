package com.course.service.iface;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.course.dto.request.CourseCertificateRequestDTO;
import com.course.dto.response.CourseCertificateResponseDTO;

@Service
public interface CourseCertificateService {
	
	public CourseCertificateResponseDTO create(CourseCertificateRequestDTO courseCertificateRequestDTO);

	public List<CourseCertificateResponseDTO> findAll();

	public CourseCertificateResponseDTO update(Integer id, CourseCertificateRequestDTO courseCertificateRequestDTO);

	public CourseCertificateResponseDTO findById(Integer id);

	public CourseCertificateResponseDTO deleteById(Integer id);
	
	public ResponseEntity<?> getPagedCourseCertificates(int page, int size);
}
