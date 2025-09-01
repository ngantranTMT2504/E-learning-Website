package com.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.course.model.CourseRegistration;

public interface CourseRegistrationRepository extends JpaRepository<CourseRegistration, Integer>, JpaSpecificationExecutor<CourseRegistration> {

}
