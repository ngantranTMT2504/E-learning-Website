package com.course.service.iface;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.course.model.Commission;

import jakarta.persistence.criteria.Predicate;

@Service
public class CommissionFilterService {
	public static Specification<Commission> filterSpec(
			
			
			Double percentage,

			Date applicableDate
			
		) {
		
		return (root, query, cb) -> {
			List<Predicate> predicates = new ArrayList<>();
			 if (percentage != null) {
	                predicates.add(cb.greaterThanOrEqualTo(root.get("percentage"), percentage));
	            }


			if (applicableDate != null) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(applicableDate);
				cal.add(Calendar.DATE, 1);
				Date nextDay = cal.getTime();

				predicates.add(cb.greaterThanOrEqualTo(root.get("applicableDate"), applicableDate));
				predicates.add(cb.lessThan(root.get("applicableDate"), nextDay));
			}

			
			
			return cb.and(predicates.toArray(new Predicate[0]));
		};
	}
}
