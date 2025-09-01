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
@Table(name="Reports")
public class Report {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(columnDefinition = "nvarchar(250)")
	private String reason;
	
	@Column(columnDefinition = "nvarchar(MAX)")
	private String discription;
	
	@Temporal(TemporalType.DATE)
	private Date reportDate;
	
	@ManyToOne
	@JoinColumn(name="lessonId", nullable = false)
	private Lesson lesson;
	
	@ManyToOne
	@JoinColumn(name="studentId", nullable = false)
	private Student student;

}
