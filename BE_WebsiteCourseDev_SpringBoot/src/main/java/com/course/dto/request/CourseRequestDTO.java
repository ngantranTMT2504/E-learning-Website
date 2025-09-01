package com.course.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Date;

import org.hibernate.validator.constraints.Range;

@Data
public class CourseRequestDTO {

    @NotBlank(message = "Tiêu đề không được để trống")
    @Size(min = 5, max = 250, message = "Độ dài tên phải từ {min} đến {max} ký tự")
    private String title;

    @NotBlank(message = "Slug không được để trống")
    private String slug;
    
    @NotBlank(message = "Hình không được để trống")
    private String image;

    private String discription;
    
    @NotBlank(message = "Mô tả ngắn không được để trống")
    private String shortDiscription;

    @NotNull(message = "Giá không được để trống")
    @Min(value = 0, message = "Giá phải >= 0")
    private int price;
    
    @NotNull(message = "Giảm giá không được để trống")
    @Range(min = 0, max = 100, message = "Giảm giá phải nằm trong khoảng từ 0 đến 100")
    private double discount;
    
    private Date createdDate;
    
    private Date updatedDate;
    
    private double averageRating;

    private boolean status;
    
    @NotNull(message = "teacherId không được để trống")
    private Integer teacherId;
    
    @NotNull(message = "courseTypeId không được để trống")
    private Integer courseTypeId;
    
    @NotNull(message = "commissionId không được để trống")
    private Integer commissionId;

}
