package com.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


import com.course.model.Lesson;

public interface LessonRepository extends JpaRepository<Lesson, Integer> ,JpaSpecificationExecutor<Lesson> {

}
