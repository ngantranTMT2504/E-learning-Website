package com.course.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="Courses")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(columnDefinition = "nvarchar(250)")
	private String title;
	
	@Column(columnDefinition = "varchar(250)", unique = true)
	private String slug;
	
	@Column(columnDefinition = "nvarchar(250)")
	private String image;
	
	@Column(columnDefinition = "nvarchar(MAX)")
	private String discription;
	
	@Column(columnDefinition = "nvarchar(MAX)")
	private String shortDiscription;
	
	private int price;
	
	private double discount;
	
	@Temporal(TemporalType.DATE)
	private Date createdDate;
	
	@Temporal(TemporalType.DATE)
	private Date updatedDate;
	
	private double averageRating;
	
	private boolean status;
	
	@ManyToOne
	@JoinColumn(name="teacherId", nullable = false)
	private Teacher teacher;
	
	@ManyToOne
	@JoinColumn(name="courseTypeId", nullable = false)
	private CourseType courseType;
	
	@ManyToOne
	@JoinColumn(name="commissionId", nullable = false)
	private Commission commission;
	
	@OneToMany(mappedBy = "course")
	private List<Lesson> lessons;
	
	@OneToMany(mappedBy = "course")
	private List<CourseCertificate> courseCertificates;
	
	@OneToMany(mappedBy = "course")
	private List<CourseRegistration> courseRegistrations;
}
