package com.course.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="CourseRegistrations")
public class CourseRegistration {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private int totalPrice;
	
	private double commission;
	
	private double discount;
	
	@Column(columnDefinition = "varchar(100)")
	private String transactionCode;
	
	@Temporal(TemporalType.DATE)
	private Date registrationDate;
	
	private boolean status;
	
	@ManyToOne
	@JoinColumn(name="studentId", nullable = false)
	private Student student;
	
	@ManyToOne
	@JoinColumn(name="courseId", nullable = false)
	private Course course;

}
