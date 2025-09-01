package com.course.service.iface;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import jakarta.persistence.criteria.Predicate; 
import com.course.model.Report;

@Service
public class ReportFilterService {
    public static Specification<Report> filterSpec(
                                    String keyword,       
                                    Integer lessonId,     
                                    Integer studentId,   
                                    Date startDate,      
                                    Date endDate   ){
         return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Tìm theo reason hoặc description
            if (keyword != null && !keyword.isBlank()) {
                Predicate reasonPredicate = cb.like(cb.lower(root.get("reason")), "%" + keyword.toLowerCase() + "%");
                Predicate descPredicate = cb.like(cb.lower(root.get("discription")), "%" + keyword.toLowerCase() + "%");
                predicates.add(cb.or(reasonPredicate, descPredicate));
            }

            //Lọc theo lessonId
            if (lessonId != null) {
                predicates.add(cb.equal(root.get("lesson").get("id"), lessonId));
            }

            // Lọc theo studentId
            if (studentId != null) {
                predicates.add(cb.equal(root.get("student").get("id"), studentId));
            }

            //Lọc theo khoảng thời gian (reportDate)
            if (startDate != null && endDate != null) {
                predicates.add(cb.between(root.get("reportDate"), startDate, endDate));
            } else if (startDate != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("reportDate"), startDate));
            } else if (endDate != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("reportDate"), endDate));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
