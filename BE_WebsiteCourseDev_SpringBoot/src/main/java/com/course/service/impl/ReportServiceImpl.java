package com.course.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.course.dto.request.ReportRequestDTO;
import com.course.dto.response.ReportResponseDTO;
import com.course.exception.ResourceNotFoundException;
import com.course.mapper.ReportMapper;
import com.course.model.Report;
import com.course.payload.PaginationResponse;
import com.course.repository.ReportRepository;
import com.course.service.iface.ReportFilterService;
import com.course.service.iface.ReportService;

@Service
public class ReportServiceImpl implements ReportService{

    @Autowired
    private ReportRepository reportRepository;

    @Override
    public ReportResponseDTO create(ReportRequestDTO requestDTO) {
        Report report = ReportMapper.toEntity(requestDTO);
        return ReportMapper.toDTO(reportRepository.save(report));
    }

    @Override
    public ReportResponseDTO delete(Integer id) {
        ReportResponseDTO dto = findById(id);
        reportRepository.deleteById(id);   
        return dto;
    }

    @Override
    public PaginationResponse<ReportResponseDTO> findAll(Pageable pageable, String keyword, Integer lessonId,
                                                        Integer studentId, Date startDate, Date endDate) {
        Specification<Report> spec = ReportFilterService.filterSpec(
            keyword,
            lessonId,
            studentId,
            startDate,
            endDate);
        Page<Report> pageResult = reportRepository.findAll(spec, pageable);
        List<ReportResponseDTO> dtos = pageResult.map(ReportMapper :: toDTO).getContent();
        return PaginationResponse.<ReportResponseDTO>builder()
                                    .data(dtos)
                                    .currentPage(pageResult.getNumber()) 
                                    .totalItems(pageResult.getTotalElements()) 
                                    .totalPages(pageResult.getTotalPages())   
                                    .build();
    }

    @Override
    public ReportResponseDTO findById(Integer id) {
        Report dto = reportRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("report not found"));
        return ReportMapper.toDTO(dto);
    }

    @Override
    public ReportResponseDTO update(Integer id, ReportRequestDTO requestDTO) {
        findById(id);
        Report report = ReportMapper.toEntity(requestDTO);
        report.setId(id);
        return ReportMapper.toDTO(reportRepository.save(report));
    }
    
    
}
