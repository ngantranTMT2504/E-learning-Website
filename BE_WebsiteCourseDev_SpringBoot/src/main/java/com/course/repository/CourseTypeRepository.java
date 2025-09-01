package com.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.course.model.CourseType;

public interface CourseTypeRepository extends JpaRepository<CourseType, Integer> ,JpaSpecificationExecutor<CourseType> {

}
