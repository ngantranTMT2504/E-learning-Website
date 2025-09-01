package com.course.mapper;

import java.util.Date;

import com.course.dto.request.LessonRequestDTO;
import com.course.dto.response.LessonResponseDTO;
import com.course.model.Course;
import com.course.model.Lesson;

public class LessonMapper {

	public static Lesson toEntity(LessonRequestDTO dto) {
		Lesson lesson = new Lesson();

		lesson.setTitle(dto.getTitle());
		lesson.setDiscription(dto.getDiscription());
		lesson.setVideoUrl(dto.getVideoUrl());
		lesson.setExerciseUrl(dto.getExerciseUrl());
		lesson.setLessonOrder(dto.getLessonOrder());
		lesson.setPostedDate(dto.getPostedDate() != null ? dto.getPostedDate() : new Date());

		lesson.setStatus(dto.isStatus());

		Course course = new Course();
		course.setId(dto.getCourseId());
		lesson.setCourse(course);

		return lesson;
	}

	public static LessonResponseDTO toResponse(Lesson lesson) {
		LessonResponseDTO dto = new LessonResponseDTO();
		dto.setId(lesson.getId());
		dto.setTitle(lesson.getTitle());

		dto.setDiscription(lesson.getDiscription());

		dto.setVideoUrl(lesson.getVideoUrl());
		dto.setExerciseUrl(lesson.getExerciseUrl());

		dto.setPostedDate(lesson.getPostedDate());
		dto.setLessonOrder(lesson.getLessonOrder());
		dto.setStatus(lesson.isStatus());

		// getCourse().getTeacher().getId()

		if (lesson.getCourse() != null) {
			
			dto.setCourseId(lesson.getCourse().getId());
			dto.setCourseName(lesson.getCourse().getTitle());
			if (lesson.getCourse().getTeacher() != null) {
				dto.setTeacherId(lesson.getCourse().getTeacher().getId());

				if (lesson.getCourse().getTeacher().getAccount() != null) {
					dto.setAccountId(lesson.getCourse().getTeacher().getAccount().getId());
					dto.setTeacherName(lesson.getCourse().getTeacher().getAccount().getFullname());
				}
			}
			
	
			if (lesson.getCourse().getCourseType() != null) {
				dto.setCourseTypeId(lesson.getCourse().getCourseType().getId());
				dto.setCourseTypeName(lesson.getCourse().getCourseType().getName());

			}
		
			if (lesson.getCourse().getCommission() != null) {
				dto.setComissionId(lesson.getCourse().getCommission().getId());
				dto.setComissionPercent(lesson.getCourse().getCommission().getPercentage());
			}
		}
		
		return dto;
	}
}
