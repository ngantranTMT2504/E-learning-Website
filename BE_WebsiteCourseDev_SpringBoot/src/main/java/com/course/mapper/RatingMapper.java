package com.course.mapper;


import com.course.dto.request.RatingRequestDTO;
import com.course.dto.response.RatingResponseDTO;
import com.course.model.Account;
import com.course.model.Lesson;
import com.course.model.Rating;
import com.course.model.Student;

public class RatingMapper {
    public static RatingResponseDTO toDTO(Rating rating){
        Lesson lesson = rating.getLesson() != null ? rating.getLesson() : null;
        Account student = rating.getStudent() != null ? rating.getStudent().getAccount() : null;

        return RatingResponseDTO.builder()
                .id(rating.getId())
                .rating(rating.getRating())
                .content(rating.getContent())
                .createdDate(rating.getCreatedDate())
                .lessonId(lesson != null ? lesson.getId() : null)
                .lessonName(lesson != null ? lesson.getTitle() : null)
                .studentId(rating.getStudent() != null ? rating.getStudent().getId() : null)
                .studentName(student != null ? student.getFullname() : null)
                .studentEmail(student != null ? student.getEmail() : null)
                .studentAvatar(student != null ? student.getAvatar() : null)
                .build();
    }

    public static Rating toEntity(RatingRequestDTO dto) {
        if (dto == null) return null;

        Rating rating = new Rating();
        rating.setRating(dto.getRating());
        rating.setContent(dto.getContent());
        Lesson lesson = new Lesson();
        lesson.setId(dto.getLessonId());
        rating.setLesson(lesson);

        Student student = new Student();
        student.setId(dto.getStudentId());
        rating.setStudent(student);

        return rating;
    }

}
