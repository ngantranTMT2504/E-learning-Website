package com.course.service.iface;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.course.model.Lesson;

import jakarta.persistence.criteria.Predicate;

@Service
public class LessonFilterService {
	public static Specification<Lesson> filterSpec(
			
			Date postedDate,
			Integer lessonOrder,
			String title,
			String courseName, 
			Boolean status, 
			List<Integer> courseIds,
			List<Integer> teacherIds,
			Integer accountId
		) {
		
		return (root, query, cb) -> {
			query.distinct(true); 
			List<Predicate> predicates = new ArrayList<>();
			if (title != null && !title.isBlank()) {
				Predicate titlePredicate = cb.like(cb.lower(root.get("title")), "%" + title.toLowerCase() + "%");
				predicates.add(titlePredicate);
			}

			if (lessonOrder != null) {
				predicates.add(cb.equal(root.get("lessonOrder"), lessonOrder));
			}
			if (postedDate != null) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(postedDate);
				cal.add(Calendar.DATE, 1);
				Date nextDay = cal.getTime();

				predicates.add(cb.greaterThanOrEqualTo(root.get("postedDate"), postedDate));
				predicates.add(cb.lessThan(root.get("postedDate"), nextDay));
			}

			if (status != null) {
				predicates.add(cb.equal(root.get("status"), status));
			}

			if (courseIds != null && !courseIds.isEmpty()) {
				predicates.add(root.get("course").get("id").in(courseIds));
			}

			if (courseName != null && !courseName.isBlank()) {
				Predicate courseNamePredicate = cb.like(cb.lower(root.get("course").get("title")), "%" + courseName.toLowerCase() + "%");
				predicates.add(courseNamePredicate);
			}
			
			if (teacherIds != null && !teacherIds.isEmpty()) {
				predicates.add(root.get("course").get("teacher").get("id").in(teacherIds));
			}
			
			if (accountId != null) {
				predicates.add(root.get("course").get("teacher").get("account").get("id").in(accountId));
			}
			
			return cb.and(predicates.toArray(new Predicate[0]));
		};
	}
}
