package com.course.service.iface;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.course.model.TeacherCertificate;

import jakarta.persistence.criteria.Predicate;

@Service
public class TeacherCertificateFilterService {

	public static Specification<TeacherCertificate> filterSpec(String certificateName, Integer issuedYear,
			String issuingOrganization,
			Date uploadedDate,List<Integer> teacherId) {
		return (root, query, cb) -> {
			List<Predicate> predicates = new ArrayList<>();

			if (certificateName != null && !certificateName.isBlank()) {
				Predicate titlePredicate = cb.like(cb.lower(root.get("certificateName")), "%" + certificateName.toLowerCase() + "%");
				predicates.add(titlePredicate);
			}
			 if (issuedYear != null) {
	                predicates.add(cb.greaterThanOrEqualTo(root.get("issuedYear"), issuedYear));
	            }
			if (uploadedDate != null) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(uploadedDate);
				cal.add(Calendar.DATE, 1);
				Date nextDay = cal.getTime();

				predicates.add(cb.greaterThanOrEqualTo(root.get("uploadedDate"), uploadedDate));
				predicates.add(cb.lessThan(root.get("uploadedDate"), nextDay));
			}
			  if(issuingOrganization != null && !issuingOrganization.isBlank()) {
	            	Predicate titlePredicate = cb.like(cb.lower(root.get("issuingOrganization")), "%" + issuingOrganization.toLowerCase() + "%");
	            	predicates.add(titlePredicate);
	            }
		
			

			return cb.and(predicates.toArray(new Predicate[0]));
		};
	}

}
