package com.course.service.impl;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.course.dto.request.CommentRequestDTO;
import com.course.dto.response.CommentResponseDTO;
import com.course.exception.ResourceNotFoundException;
import com.course.mapper.CommentMapper;
import com.course.model.Comment;
import com.course.payload.PaginationResponse;
import com.course.repository.CommentRepository;
import com.course.service.iface.CommentFilterService;
import com.course.service.iface.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public PaginationResponse<CommentResponseDTO> findAll(
            Pageable pageable,
            List<Integer> lessonIds,
            List<Integer> courseIds) {

        Specification<Comment> specification = CommentFilterService.filterSpec(lessonIds, courseIds, true);
        Page<Comment> pageResult = commentRepository.findAll(specification, pageable);
        List<CommentResponseDTO> dtos = pageResult.getContent().stream()
                .map(CommentMapper::toResponse)
                .toList();

        return PaginationResponse.<CommentResponseDTO>builder()
                .data(dtos)
                .currentPage(pageResult.getNumber())
                .totalItems(pageResult.getTotalElements())
                .totalPages(pageResult.getTotalPages())
                .build();
    }

    @Override
    public CommentResponseDTO findById(Integer id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment không tồn tại"));
        return CommentMapper.toResponse(comment);
    }

    @Override
    public CommentResponseDTO create(CommentRequestDTO commentRequestDTO) {
        Comment comment = CommentMapper.toEntity(commentRequestDTO);
        comment.setCountDislike(0);
        comment.setCountLike(0);
        comment.setCreatedDate(Date.from(Instant.now())); // UTC
        return CommentMapper.toResponse(commentRepository.save(comment));
    }

    @Override
    public CommentResponseDTO update(Integer id, CommentRequestDTO commentRequestDTO) {
        CommentResponseDTO commentResponseDTO = findById(id);
        Comment comment = CommentMapper.toEntity(commentRequestDTO);
        comment.setId(id);
        comment.setCreatedDate(commentResponseDTO.getCreatedDate());
        return CommentMapper.toResponse(commentRepository.save(comment));
    }

    @Override
    public CommentResponseDTO delete(Integer id) {
        CommentResponseDTO commentResponseDTO = findById(id);
        commentRepository.deleteById(id);
        return commentResponseDTO;
    }

}
