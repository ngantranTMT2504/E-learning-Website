package com.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.course.model.TeacherCertificate;

public interface TeacherCertificateRepository
		extends JpaRepository<TeacherCertificate, Integer>, JpaSpecificationExecutor<TeacherCertificate> {

}
