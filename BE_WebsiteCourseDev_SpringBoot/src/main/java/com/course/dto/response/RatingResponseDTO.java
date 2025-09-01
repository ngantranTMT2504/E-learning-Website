package com.course.dto.response;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RatingResponseDTO {
    private Integer id;
    private int rating;
    private String content;
    private Date createdDate;
    private Integer lessonId;
    private String lessonName;
    private Integer studentId;
    private String studentName;
    private String studentEmail;
    private String studentAvatar;
}
