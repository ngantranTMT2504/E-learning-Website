package com.course.service.iface;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import jakarta.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.course.model.Course;

@Service
public class CourseFilterService {

    public static Specification<Course> filterSpec(
            String keyword,
            String title,
            String slug,
            Integer minPrice,
            Integer maxPrice,
            List<Integer> typeIds,
            List<Integer> teacherIds,
            Integer accountId,
            Double discount,
            Date createdDate,
            Date updatedDate,
            Double averageRating,
            Boolean status
    ) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            
            if(title != null && !title.isBlank()) {
            	Predicate titlePredicate = cb.like(cb.lower(root.get("title")), "%" + title.toLowerCase() + "%");
            	predicates.add(titlePredicate);
            }
            
            if(slug != null && !slug.isBlank()) {
            	Predicate slugPredicate = cb.like(cb.lower(root.get("slug")), slug.toLowerCase());
            	predicates.add(slugPredicate);
            }

            if (keyword != null && !keyword.isBlank()) {
                Predicate titlePredicate = cb.like(cb.lower(root.get("title")), "%" + keyword.toLowerCase() + "%");
                Predicate descPredicate = cb.like(cb.lower(root.get("discription")), "%" + keyword.toLowerCase() + "%");
                Predicate shortDescPredicate = cb.like(cb.lower(root.get("shortDiscription")), "%" + keyword.toLowerCase() + "%");
                predicates.add(cb.or(titlePredicate,descPredicate, shortDescPredicate));
            }

            if (minPrice != null && maxPrice != null) {
                predicates.add(cb.between(root.get("price"), minPrice, maxPrice));
            }

            if (typeIds != null && !typeIds.isEmpty()) {
                predicates.add(root.get("courseType").get("id").in(typeIds));
            }
            
            if (teacherIds != null && !teacherIds.isEmpty()) {
                predicates.add(root.get("teacher").get("id").in(teacherIds));
            }
            
            if (accountId != null) {
                predicates.add(root.get("teacher").get("account").get("id").in(accountId));
            }

            if (discount != null) {
                predicates.add(cb.equal(root.get("discount"), discount));
            }

            if (createdDate != null) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(createdDate);
                cal.add(Calendar.DATE, 1);
                Date nextDay = cal.getTime();

                predicates.add(cb.greaterThanOrEqualTo(root.get("createdDate"), createdDate));
                predicates.add(cb.lessThan(root.get("createdDate"), nextDay));
            }
            
            if (updatedDate != null) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(updatedDate);
                cal.add(Calendar.DATE, 1);
                Date nextDay = cal.getTime();

                predicates.add(cb.greaterThanOrEqualTo(root.get("updatedDate"), updatedDate));
                predicates.add(cb.lessThan(root.get("updatedDate"), nextDay));
            }

            if (averageRating != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("averageRating"), averageRating));
            }

            if (status != null) {
                predicates.add(cb.equal(root.get("status"), status));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

}

