package com.course.dto.response;

import java.util.Date;

import lombok.Data;

@Data
public class CourseResponseDTO {
	
	private Integer id;
	private String title;
	private String slug;
	private String image;
	private String discription;
	private String shortDiscription;
	private int price;
	private double discount;
	private Date createdDate;
	private Date updatedDate;
	private double averageRating;
	private boolean status;
	
	private Integer accountId;
	private Integer teacherId; 
	private Integer courseTypeId;
	private Integer commissionId;
	
	private String teacherName;
	private String courseTypeName;
	private double commissionPercentage;
	private int lessonTotal;
}
