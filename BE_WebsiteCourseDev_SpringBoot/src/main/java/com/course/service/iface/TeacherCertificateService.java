package com.course.service.iface;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.course.dto.request.TeacherCertificateRequestDTO;
import com.course.dto.response.TeacherCertificateResponseDTO;

@Service
public interface TeacherCertificateService {
	public TeacherCertificateResponseDTO create(TeacherCertificateRequestDTO teacherCertificateRequestDTO);

	public List<TeacherCertificateResponseDTO> findAll();

	public TeacherCertificateResponseDTO update(Integer id, TeacherCertificateRequestDTO teacherCertificateRequestDTO);

	public TeacherCertificateResponseDTO findById(Integer id);

	public TeacherCertificateResponseDTO deleteById(Integer id);

	public ResponseEntity<?> getPagedTeacherCertificateByManyParams(String certificateName, String certificateImage,Integer issuedYear,
	String issuingOrganization,Date uploadedDate,List<Integer> teacherId,Integer accountId, Pageable pageable);
}
