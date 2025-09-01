package com.course.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.course.dto.request.CommentRequestDTO;
import com.course.dto.response.CommentResponseDTO;
import com.course.payload.PaginationResponse;
import com.course.service.iface.CommentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @GetMapping
    public ResponseEntity<?> findAll(
                                    Pageable pageable,
                                    @RequestParam(name = "lessonIds", required = false) List<Integer> lessonIds,
                                    @RequestParam(name = "courseIds", required = false) List<Integer> courseIds){
        PaginationResponse<CommentResponseDTO> response = commentService.findAll(pageable, lessonIds, courseIds);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") Integer id){
        CommentResponseDTO response = commentService.findById(id);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity<?> createComment(@RequestBody @Valid CommentRequestDTO dto){
        CommentResponseDTO response = commentService.create(dto);
        return ResponseEntity.status(201).body(response);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateComment(@PathVariable(name = "id") Integer id, @RequestBody @Valid CommentRequestDTO dto){
        CommentResponseDTO response = commentService.update(id, dto);
        return ResponseEntity.ok().body(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable(name = "id") Integer id){
        CommentResponseDTO response = commentService.delete(id);
        return ResponseEntity.ok().body(response);
    }
}
