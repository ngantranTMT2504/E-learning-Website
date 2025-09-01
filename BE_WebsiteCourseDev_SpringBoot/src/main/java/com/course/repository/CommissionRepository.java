package com.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.course.model.Commission;

public interface CommissionRepository extends JpaRepository<Commission, Integer> ,JpaSpecificationExecutor<Commission> {

}
