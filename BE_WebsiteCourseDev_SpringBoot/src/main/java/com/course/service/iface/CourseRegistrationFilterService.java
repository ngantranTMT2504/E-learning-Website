package com.course.service.iface;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.course.model.CourseRegistration;

import jakarta.persistence.criteria.Predicate;

@Service
public class CourseRegistrationFilterService {

	public static Specification<CourseRegistration> filterSpec(
			String transactionCode,
			Integer minPrice,
			Integer maxPrice,
			Double commission,
			Double discount,
			Date registrationDate,
			Boolean status,
			List<Integer> studentIds,
			List<Integer> courseIds
			){
		return (root, query, cb) -> {
			List<Predicate> predicates = new ArrayList<>();
			
			if(transactionCode != null && !transactionCode.isBlank()) {
            	Predicate transactionCodePredicate = cb.like(cb.lower(root.get("transactionCode")), transactionCode.toLowerCase());
            	predicates.add(transactionCodePredicate);
            }
			
			if (minPrice != null && maxPrice != null) {
	            predicates.add(cb.between(root.get("totalPrice"), minPrice, maxPrice));
	        }
			
			if (commission != null) {
                predicates.add(cb.equal(root.get("commission"), commission));
            }
			
			if (discount != null) {
                predicates.add(cb.equal(root.get("discount"), discount));
            }
            
            if (registrationDate != null) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(registrationDate);
                cal.add(Calendar.DATE, 1);
                Date nextDay = cal.getTime();

                predicates.add(cb.greaterThanOrEqualTo(root.get("registrationDate"), registrationDate));
                predicates.add(cb.lessThan(root.get("registrationDate"), nextDay));
            }
			
			if (status != null) {
                predicates.add(cb.equal(root.get("status"), status));
            }
			
			if (studentIds != null && !studentIds.isEmpty()) {
                predicates.add(root.get("student").get("id").in(studentIds));
            }
			
			if (courseIds != null && !courseIds.isEmpty()) {
                predicates.add(root.get("course").get("id").in(courseIds));
            }
			
			return cb.and(predicates.toArray(new Predicate[0]));
		};	
	}
}
