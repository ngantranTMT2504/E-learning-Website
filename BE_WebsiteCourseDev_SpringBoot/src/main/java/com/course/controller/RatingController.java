package com.course.controller;

import java.util.Date;
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

import com.course.dto.request.RatingRequestDTO;
import com.course.dto.response.RatingResponseDTO;
import com.course.payload.PaginationResponse;
import com.course.service.iface.RatingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {
    @Autowired
    private RatingService ratingService;
    @GetMapping
    public ResponseEntity<?> findAll(
                Pageable pageable,
                @RequestParam(name = "minRating", required = false) Integer minRating,
                @RequestParam(name = "maxRating", required = false) Integer maxRating,
                @RequestParam(name = "startDate", required = false) Date startDate,
                @RequestParam(name = "endDate", required = false) Date endDate,
                @RequestParam(name = "studentIds", required = false) List<Integer> studentIds,
                @RequestParam(name = "contentKeyword", defaultValue = "") String contentKeyword,
                @RequestParam(name = "lessonIds", required = false) List<Integer> lessonIds,
                @RequestParam(name = "courseIds", required = false) List<Integer> courseIds
        ){
        PaginationResponse<RatingResponseDTO> response = ratingService.findAll(pageable, minRating, maxRating, contentKeyword, startDate, endDate, lessonIds, studentIds, courseIds);
        return ResponseEntity.ok().body(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") Integer id){
        RatingResponseDTO response = ratingService.findById(id);
        return ResponseEntity.ok().body(response);
    }
    @PostMapping
    public ResponseEntity<?> createRating(@RequestBody @Valid RatingRequestDTO dto){
        RatingResponseDTO response = ratingService.create(dto);
        return ResponseEntity.status(201).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRating(@PathVariable(name = "id") Integer id, @RequestBody @Valid RatingRequestDTO requestDTO){
        RatingResponseDTO response = ratingService.update(id, requestDTO);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRating(@PathVariable(name = "id") Integer id){
        RatingResponseDTO response = ratingService.delete(id);
        return ResponseEntity.ok().body(response);
    }
}
