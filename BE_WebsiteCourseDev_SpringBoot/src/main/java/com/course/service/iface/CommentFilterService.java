package com.course.service.iface;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.course.model.Comment;

import jakarta.persistence.criteria.Predicate;

public class CommentFilterService {
    public static Specification<Comment> filterSpec(
            List<Integer> lessonIds,
            List<Integer> courseIds,
            boolean onlyParent) {

        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (lessonIds != null && !lessonIds.isEmpty()) {
                predicates.add(root.get("lesson").get("id").in(lessonIds));
            }

            if (courseIds != null && !courseIds.isEmpty()) {
                predicates.add(root.get("lesson").get("course").get("id").in(courseIds));
            }

            // Chỉ lấy comment cha
            if (onlyParent) {
                predicates.add(cb.isNull(root.get("parent")));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
