package com.course.service.iface;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.course.model.TeacherBank;

import jakarta.persistence.criteria.Predicate;

@Service
public class TeacherBankFilterService {

	public static Specification<TeacherBank> filterSpec(String bankName, Date createDate, Date uploadedDate,
			Boolean isDefault, List<Integer> teacherId) {
		return (root, query, cb) -> {
			List<Predicate> predicates = new ArrayList<>();

			if (bankName != null && !bankName.isBlank()) {
				Predicate titlePredicate = cb.like(cb.lower(root.get("bankName")), "%" + bankName.toLowerCase() + "%");
				predicates.add(titlePredicate);
			} if (teacherId != null && !teacherId.isEmpty()) {
                predicates.add(root.get("teacher").get("id").in(teacherId));
            }

			if (createDate != null) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(createDate);
				cal.add(Calendar.DATE, 1);
				Date nextDay = cal.getTime();

				predicates.add(cb.greaterThanOrEqualTo(root.get("createDate"), createDate));
				predicates.add(cb.lessThan(root.get("createDate"), nextDay));
			}

			if (uploadedDate != null) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(uploadedDate);
				cal.add(Calendar.DATE, 1);
				Date nextDay = cal.getTime();

				predicates.add(cb.greaterThanOrEqualTo(root.get("uploadedDate"), uploadedDate));
				predicates.add(cb.lessThan(root.get("uploadedDate"), nextDay));
			}if (isDefault != null) {
				predicates.add(cb.equal(root.get("isDefault"), isDefault));
			}

			return cb.and(predicates.toArray(new Predicate[0]));
		};
	}

}
