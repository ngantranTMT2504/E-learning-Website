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

import com.course.dto.request.TeacherCertificateRequestDTO;
import com.course.dto.response.TeacherCertificateResponseDTO;
import com.course.exception.ResourceNotFoundException;
import com.course.mapper.TeacherCertificateMapper;
import com.course.model.Teacher;
import com.course.model.TeacherCertificate;
import com.course.repository.TeacherCertificateRepository;
import com.course.repository.TeacherRepository;
import com.course.service.iface.TeacherCertificateFilterService;
import com.course.service.iface.TeacherCertificateService;

@Service
public class TeacherCertificateServiceImpl implements TeacherCertificateService {

	@Autowired
	private TeacherCertificateRepository teacherCertificateRepository;
	@Autowired
	private TeacherRepository teacherRepository;

	@Override
	public TeacherCertificateResponseDTO create(TeacherCertificateRequestDTO teacherCertificateRequestDTO) {
		TeacherCertificate teacherCertificate = TeacherCertificateMapper.toEntity(teacherCertificateRequestDTO);
		TeacherCertificate saved = teacherCertificateRepository.save(teacherCertificate);
		TeacherCertificateResponseDTO teacherCertificateResponseDTO = TeacherCertificateMapper.toResponse(saved);
		return teacherCertificateResponseDTO;
	}

	@Override
	public List<TeacherCertificateResponseDTO> findAll() {
		List<TeacherCertificateResponseDTO> responseList = teacherCertificateRepository.findAll().stream()
				.map(TeacherCertificateMapper::toResponse)
				.sorted(Comparator.comparing(TeacherCertificateResponseDTO::getCertificateName).reversed()

						.thenComparing(TeacherCertificateResponseDTO::getUploadedDate))
				.collect(Collectors.toList());
		return responseList;
	}

	@Override
	public TeacherCertificateResponseDTO update(Integer id, TeacherCertificateRequestDTO teacherCertificateRequestDTO) {
		findById(id);
		TeacherCertificate updatedTeacherCertificate = TeacherCertificateMapper.toEntity(teacherCertificateRequestDTO);
		updatedTeacherCertificate.setId(id);
		TeacherCertificate update = teacherCertificateRepository.save(updatedTeacherCertificate);
		TeacherCertificateResponseDTO teacherCertificateResponseDTO = TeacherCertificateMapper.toResponse(update);
		return teacherCertificateResponseDTO;
	}

	@Override
	public TeacherCertificateResponseDTO findById(Integer id) {
		TeacherCertificate teacherCertificate = teacherCertificateRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Không tìm chứng chỉ giáo viên"));
		TeacherCertificateResponseDTO teacherCertificateResponseDTO = TeacherCertificateMapper
				.toResponse(teacherCertificate);
		return teacherCertificateResponseDTO;
	}

	@Override
	public TeacherCertificateResponseDTO deleteById(Integer id) {
		TeacherCertificateResponseDTO teacherCertificateResponseDTO = findById(id);
		teacherCertificateRepository.deleteById(id);
		return teacherCertificateResponseDTO;
	}

	@Override
	public ResponseEntity<?> getPagedTeacherCertificateByManyParams(
	    String certificateName,
	    String certificateImage,
	    Integer issuedYear,
	    String issuingOrganization,
	    Date uploadedDate,
	    List<Integer> teacherId,
	    Integer accountId, // <-- accountId được truyền vào
	    Pageable pageable
	) {
	    // Gọi service lọc thông minh
	    Specification<TeacherCertificate> spec = TeacherCertificateFilterService
	        .filterSpec(certificateName, issuedYear, issuingOrganization, uploadedDate, teacherId);

	    // Nếu có accountId thì lọc thêm theo teacher.account.id
	    if (accountId != null) {
	        spec = spec.and((root, query, cb) ->
	            cb.equal(root.get("teacher").get("account").get("id"), accountId)
	        );
	        System.out.println("DEBUG >> Đang lọc theo accountId = " + accountId);
	    }

	    Page<TeacherCertificate> pageResult = teacherCertificateRepository.findAll(spec, pageable);
	    List<TeacherCertificateResponseDTO> result = pageResult
	        .stream()
	        .map(TeacherCertificateMapper::toResponse)
	        .toList();

	    Map<String, Object> response = new HashMap<>();
	    response.put("data", result);
	    response.put("currentPage", pageResult.getNumber());
	    response.put("totalItems", pageResult.getTotalElements());
	    response.put("totalPages", pageResult.getTotalPages());

	    return ResponseEntity.ok(response);
	}


}
