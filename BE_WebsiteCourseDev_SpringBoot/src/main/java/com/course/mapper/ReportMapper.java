package com.course.mapper;

import com.course.dto.request.ReportRequestDTO;
import com.course.dto.response.ReportResponseDTO;
import com.course.model.Account;
import com.course.model.Lesson;
import com.course.model.Report;
import com.course.model.Student;

public class ReportMapper {
    public static ReportResponseDTO toDTO(Report report) {
        if (report == null) return null;

        Lesson lesson = report.getLesson();
        Student student = report.getStudent();
        Account account = (student != null) ? student.getAccount() : null;

        return ReportResponseDTO.builder()
                .id(report.getId())
                .reason(report.getReason())
                .discription(report.getDiscription())
                .reportDate(report.getReportDate())
                .lessonId((lesson != null) ? lesson.getId() : null)
                .lessonName((lesson != null) ? lesson.getTitle() : null)
                .studentId((student != null) ? student.getId() : null)
                .nameStudent((account != null) ? account.getFullname() : null)
                .emailStudent((account != null) ? account.getEmail() : null)
                .build();
    }

    public static Report toEntity(ReportRequestDTO dto){
        Report report = new Report(null,
                                    dto.getReason(),
                                    dto.getDiscription(), 
                                    dto.getReportDate(), 
                                    new Lesson(dto.getLessonId(), null, null, null, null, 0, null, false, null, null, null, null, null), 
                                    new Student(dto.getStudentId(), null, null, null, null, null, null, null));
        return report;
    }
}
