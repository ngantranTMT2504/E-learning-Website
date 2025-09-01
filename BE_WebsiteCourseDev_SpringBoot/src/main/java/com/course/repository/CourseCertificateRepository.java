package com.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.course.model.CourseCertificate;

public interface CourseCertificateRepository extends JpaRepository<CourseCertificate, Integer>, JpaSpecificationExecutor<CourseCertificate> {

}
