package com.course.service.iface;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.course.model.CourseType;

import jakarta.persistence.criteria.Predicate;

@Service
public class CourseTypeFilterService {
	public static Specification<CourseType> filterSpec(
			
			
			String name, 
			Boolean status
			
		) {
		
		return (root, query, cb) -> {
			List<Predicate> predicates = new ArrayList<>();
			if (name != null && !name.isBlank()) {
				Predicate titlePredicate = cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
				predicates.add(titlePredicate);
			}


			if (status != null) {
				predicates.add(cb.equal(root.get("status"), status));
			}

			
			
			return cb.and(predicates.toArray(new Predicate[0]));
		};
	}
}
