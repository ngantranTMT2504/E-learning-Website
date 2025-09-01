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
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="Lessons")
public class Lesson {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(columnDefinition = "nvarchar(200)")
	private String title;
	
	@Column(columnDefinition = "nvarchar(MAX)")
	private String discription;
	
	@Column(columnDefinition = "varchar(MAX)")
	private String videoUrl;
	
	@Column(columnDefinition = "varchar(MAX)")
	private String exerciseUrl;
	
	private int	lessonOrder;
	
	@Temporal(TemporalType.DATE)
	private Date postedDate;
	
	private boolean status;

	@ManyToOne
	@JoinColumn(name="courseId", nullable = false)
	private Course course;
	
	@OneToMany(mappedBy = "lesson")
	private List<Progress> progresses;
	
	@OneToMany(mappedBy = "lesson")
	private List<Report> reports;
	
	@OneToMany(mappedBy = "lesson")
	private List<Rating> ratings;
	
	@OneToMany(mappedBy = "lesson")
	private List<Comment> comments;
	
}
