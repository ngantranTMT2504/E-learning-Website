package com.course.service.iface;

import java.util.Date;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.course.dto.request.ReportRequestDTO;
import com.course.dto.response.ReportResponseDTO;
import com.course.payload.PaginationResponse;

@Service
public interface ReportService {
    public PaginationResponse<ReportResponseDTO> findAll(
                                    Pageable pageable,
                                    String keyword,       
                                    Integer lessonId,     
                                    Integer studentId,   
                                    Date startDate,      
                                    Date endDate);
    public ReportResponseDTO findById(Integer id);
    public ReportResponseDTO create(ReportRequestDTO requestDTO);
    public ReportResponseDTO update(Integer id, ReportRequestDTO requestDTO);
    public ReportResponseDTO delete(Integer id);
}
