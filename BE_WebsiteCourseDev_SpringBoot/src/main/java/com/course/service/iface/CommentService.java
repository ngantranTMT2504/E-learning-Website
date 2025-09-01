package com.course.service.iface;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.course.dto.request.CommentRequestDTO;
import com.course.dto.response.CommentResponseDTO;
import com.course.payload.PaginationResponse;

@Service
public interface CommentService {
    public PaginationResponse<CommentResponseDTO> findAll(Pageable pageable,
                                                            List<Integer> lessonIds,
                                                            List<Integer> courseIds);
    public CommentResponseDTO findById(Integer id);
    public CommentResponseDTO create(CommentRequestDTO commentRequestDTO);
    public CommentResponseDTO update(Integer id, CommentRequestDTO commentRequestDTO);
    public CommentResponseDTO delete(Integer id);
}
