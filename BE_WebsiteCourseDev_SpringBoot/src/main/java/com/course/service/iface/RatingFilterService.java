package com.course.service.iface;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.course.model.Rating;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;

public class RatingFilterService {

    public static Specification<Rating> filterSpec(
            Integer minRating,
            Integer maxRating,
            String contentKeyword,
            Date startDate,
            Date endDate,
            List<Integer> lessonIds,
            List<Integer> studentIds,
            List<Integer> courseIds  
    ) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Lọc theo điểm rating
            if (minRating != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("rating"), minRating));
            }
            if (maxRating != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("rating"), maxRating));
            }

            // Lọc theo nội dung
            if (contentKeyword != null && !contentKeyword.isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("content")), "%" + contentKeyword.toLowerCase() + "%"));
            }

            // Lọc theo khoảng ngày tạo
            if (startDate != null && endDate != null) {
                predicates.add(cb.between(root.get("createdDate"), startDate, endDate));
            } else if (startDate != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("createdDate"), startDate));
            } else if (endDate != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("createdDate"), endDate));
            }

            // Lọc theo lesson
            if (lessonIds != null && !lessonIds.isEmpty()) {
                predicates.add(root.join("lesson", JoinType.INNER).get("id").in(lessonIds));
            }

            // Lọc theo student
            if (studentIds != null && !studentIds.isEmpty()) {
                predicates.add(root.join("student", JoinType.INNER).get("id").in(studentIds));
            }

            //Lọc theo course (join lesson → course)
            if (courseIds != null && !courseIds.isEmpty()) {
                Join<Object, Object> lessonJoin = root.join("lesson", JoinType.INNER);
                predicates.add(lessonJoin.join("course", JoinType.INNER).get("id").in(courseIds));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
