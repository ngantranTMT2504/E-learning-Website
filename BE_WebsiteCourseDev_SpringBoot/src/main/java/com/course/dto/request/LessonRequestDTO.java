package com.course.dto.request;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LessonRequestDTO {

    @NotBlank(message = "Tiêu đề không được để trống")
    @Size(min = 5, max = 250, message = "Độ dài tên phải từ {min} đến {max} ký tự")
    private String title;
    
    @NotBlank(message = "Mô tả không được để trống")
    private String discription;

    @NotBlank(message ="Link video không được để trống")
    private String videoUrl;
    
    @NotBlank(message ="Link exercise không được để trống")
    private String exerciseUrl;
    
    private Date postedDate;
    
    private Integer lessonOrder;
    
    private boolean status;
    
    @NotNull(message = "courseId không được để trống")
    private Integer courseId;
    

 

}
