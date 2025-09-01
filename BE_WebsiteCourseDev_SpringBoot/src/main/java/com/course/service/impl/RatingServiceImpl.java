package com.course.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.course.dto.request.RatingRequestDTO;
import com.course.dto.response.RatingResponseDTO;
import com.course.exception.ResourceNotFoundException;
import com.course.mapper.RatingMapper;
import com.course.model.Rating;
import com.course.payload.PaginationResponse;
import com.course.repository.RatingRepository;
import com.course.service.iface.RatingFilterService;
import com.course.service.iface.RatingService;

@Service
public class RatingServiceImpl implements RatingService{

    @Autowired
    private RatingRepository ratingRepository;


    @Override
    public RatingResponseDTO findById(Integer id) {
        Rating rating = ratingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy rating"));
        return RatingMapper.toDTO(rating);
    }

    @Override
    public RatingResponseDTO create(RatingRequestDTO ratingRequestDTO) {
        Rating rating = RatingMapper.toEntity(ratingRequestDTO);
        rating.setCreatedDate(new Date());
        return RatingMapper.toDTO(ratingRepository.save(rating));
    }

    @Override
    public RatingResponseDTO update(Integer id, RatingRequestDTO ratingRequestDTO) {
        RatingResponseDTO dto = findById(id);
        Rating rating = RatingMapper.toEntity(ratingRequestDTO);
        rating.setId(id);
        rating.setCreatedDate(dto.getCreatedDate());
        return RatingMapper.toDTO(ratingRepository.save(rating));
    }

    @Override
    public RatingResponseDTO delete(Integer id) {
        RatingResponseDTO dto = findById(id);
        ratingRepository.deleteById(id);
        return dto;
    }

    @Override
    public PaginationResponse<RatingResponseDTO> findAll(Pageable pageable,
                    Integer minRating, Integer maxRating, String contentKeyword, Date startDate, Date endDate,
                    List<Integer> lessonIds, List<Integer> studentIds, List<Integer> courseIds) {
        Specification<Rating> spec = RatingFilterService.filterSpec(
                                                        minRating,
                                                        maxRating,
                                                        contentKeyword,
                                                        startDate,
                                                        endDate,
                                                        lessonIds,
                                                        studentIds,
                                                        courseIds);
        Page<Rating> pageResult = ratingRepository.findAll(spec, pageable);
        List<RatingResponseDTO> dtos = pageResult.map(RatingMapper::toDTO).getContent();
        return PaginationResponse.<RatingResponseDTO>builder()
                                    .data(dtos)
                                    .currentPage(pageResult.getNumber()) 
                                    .totalItems(pageResult.getTotalElements()) 
                                    .totalPages(pageResult.getTotalPages())   
                                    .build();
    }
    
}
