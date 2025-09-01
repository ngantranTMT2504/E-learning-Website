package com.course.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="Students")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(columnDefinition = "nvarchar(MAX)")
	private String note;
	
	@OneToOne
	@JoinColumn(name="accountId", nullable = false, unique = true)
	private Account account;
	
	@OneToMany(mappedBy = "student")
	private List<CourseCertificate> courseCertificates;
	
	@OneToMany(mappedBy = "student")
	private List<CourseRegistration> courseRegistrations;
	
	@OneToMany(mappedBy = "student")
	private List<Progress> progresses;
	
	@OneToMany(mappedBy = "student")
	private List<Report> reports;
	
	@OneToMany(mappedBy = "student")
	private List<Rating> ratings;
}
