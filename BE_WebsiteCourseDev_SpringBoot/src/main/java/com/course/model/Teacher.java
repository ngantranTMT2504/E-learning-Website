package com.course.model;

import java.util.List;

import jakarta.persistence.CascadeType;
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
@Table(name="Teachers")
public class Teacher {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(columnDefinition = "nvarchar(100)")
	private String degree;
	
	@Column(columnDefinition = "varchar(12)")
	private String phone;
	
	private Long balance;
	
	@Column(columnDefinition = "nvarchar(250)")
	private String educationLevel;
	
	private boolean status;
	
	@OneToOne
	@JoinColumn(name="accountId", nullable = false, unique = true)
	private Account account;
	
	@OneToMany(mappedBy = "teacher")
	private List<Course> courses;
	
	@OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
	private List<TeacherBank> teacherBanks;
	
	@OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
	private List<TeacherCertificate> teacherCertificates;
}
