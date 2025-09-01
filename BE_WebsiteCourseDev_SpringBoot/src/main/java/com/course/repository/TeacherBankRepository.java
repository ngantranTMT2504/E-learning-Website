package com.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.course.model.TeacherBank;

public interface TeacherBankRepository
		extends JpaRepository<TeacherBank, Integer>, JpaSpecificationExecutor<TeacherBank> {

}
