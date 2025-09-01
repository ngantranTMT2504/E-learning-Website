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
@Table(name="CourseCertificates")
public class CourseCertificate {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(columnDefinition = "varchar(MAX)")
	private String certificateUrl;
	
	@Temporal(TemporalType.DATE)
	private Date issuedDate;
	
	@ManyToOne
	@JoinColumn(name="studentId", nullable = false)
	private Student student;
	
	@ManyToOne
	@JoinColumn(name="courseId", nullable = false)
	private Course course;
}
