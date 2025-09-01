package com.course.service.iface;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.course.dto.request.RatingRequestDTO;
import com.course.dto.response.RatingResponseDTO;
import com.course.payload.PaginationResponse;

@Service
public interface RatingService {
    public PaginationResponse<RatingResponseDTO> findAll(
                                    Pageable pageable,
                                    Integer minRating,
                                    Integer maxRating,
                                    String contentKeyword,
                                    Date startDate,
                                    Date endDate,
                                    List<Integer> lessonIds,
                                    List<Integer> studentIds,
                                    List<Integer> courseIds);
    public RatingResponseDTO findById(Integer id);
    public RatingResponseDTO create(RatingRequestDTO ratingRequestDTO);
    public RatingResponseDTO update(Integer id, RatingRequestDTO ratingRequestDTO);
    public RatingResponseDTO delete(Integer id);
}
