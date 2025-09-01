package com.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.course.model.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer>,JpaSpecificationExecutor<Report>{
    
}
