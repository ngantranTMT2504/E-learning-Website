package com.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.course.model.Progress;

public interface ProgressRepository extends JpaRepository<Progress, Integer>, JpaSpecificationExecutor<Progress> {

}
