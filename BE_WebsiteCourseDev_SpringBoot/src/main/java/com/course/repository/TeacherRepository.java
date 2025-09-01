package com.course.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.course.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
	List<Teacher> findByAccountId(Integer accountId);

}
